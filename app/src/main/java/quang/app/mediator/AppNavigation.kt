package quang.app.mediator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import quang.app.mediator.features.onboarding.view.OnboardingScreen
import quang.app.mediator.features.register.view.RegisterScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable("onboarding") {
             OnboardingScreen(navController) // Uncomment and implement navigation if needed
        }

        composable("register"){
             RegisterScreen(navController) // Uncomment and implement navigation if needed
        }

    }
}