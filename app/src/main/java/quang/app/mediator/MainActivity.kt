package quang.app.mediator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import quang.app.mediator.features.onboarding.view.OnboardingScreen
import quang.app.mediator.ui.theme.MediatorTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediatorTheme {
                OnboardingScreen()
            }
        }
    }
}

