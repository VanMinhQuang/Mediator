package quang.app.mediator.core.navigation

import androidx.navigation.NavController

fun NavController.navigateReplace(route: String, currentRoute: String) {
    this.navigate(route) {
        popUpTo(currentRoute) { inclusive = true }
        launchSingleTop = true

        anim {
            enter = android.R.anim.fade_in
            exit = android.R.anim.fade_out
            popEnter = android.R.anim.fade_in
            popExit = android.R.anim.fade_out
        }
    }
}
