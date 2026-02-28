package quang.app.mediator

import MainScreen
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import quang.app.mediator.features.Routes
import quang.app.mediator.features.course.view.CourseScreen
import quang.app.mediator.features.login.view.LoginScreen
import quang.app.mediator.features.onboarding.view.OnboardingScreen
import quang.app.mediator.features.play_music.view.PlayMusicScreen
import quang.app.mediator.features.register.view.RegisterScreen
import quang.app.mediator.features.welcome.reminder.view.ReminderScreen
import quang.app.mediator.features.welcome.topic.view.TopicScreen
import quang.app.mediator.features.welcome.welcome.view.WelcomeScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.ONBOARDING,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        composable(
            route = Routes.ONBOARDING
        ) { OnboardingScreen(navController) }
        composable(Routes.LOGIN) { LoginScreen(navController) }
        composable(Routes.REGISTER) { RegisterScreen(navController) }
        composable(Routes.WELCOME) { WelcomeScreen(navController) }
        composable(Routes.TOPIC) { TopicScreen(navController) }
        composable(Routes.REMINDER) { ReminderScreen(navController) }
        composable(Routes.HOME) {MainScreen(navController)}
        composable(Routes.PLAY_MUSIC) { PlayMusicScreen(navController) }
        composable(Routes.COURSE) { CourseScreen(navController) }

    }
}
