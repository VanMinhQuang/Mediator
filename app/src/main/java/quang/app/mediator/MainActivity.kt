package quang.app.mediator

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import quang.app.mediator.features.Routes
import quang.app.mediator.ui.theme.MediatorTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // A state to track if we have a pending navigation from a notification
    private var pendingNavigation by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Check intent on Cold Start
        processIntent(intent)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = Color.Transparent.toArgb(),
                darkScrim = Color.Transparent.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = Color.Transparent.toArgb(),
                darkScrim = Color.Transparent.toArgb()
            )
        )

        setContent {
            val navController = rememberNavController()

            MediatorTheme {
                AppNavigation(navController)
            }

            // Observe the pendingNavigation state
            LaunchedEffect(pendingNavigation) {
                pendingNavigation?.let { target ->
                    if (target == "login") {
                        navController.navigate(Routes.LOGIN) {
                            // Avoid multiple login screens in backstack
                            launchSingleTop = true
                        }
                    }
                    // Crucial: Reset the state so it doesn't navigate again
                    pendingNavigation = null
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        // Check intent when app is already open
        processIntent(intent)
    }

    private fun processIntent(intent: Intent?) {
        intent?.getStringExtra("navigate_to")?.let { target ->
            pendingNavigation = target
            // Optional: Remove extra so it doesn't trigger again on activity recreation
            intent.removeExtra("navigate_to")
        }
    }
}
