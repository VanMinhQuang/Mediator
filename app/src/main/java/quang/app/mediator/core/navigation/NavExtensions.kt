package quang.app.mediator.core.navigation

import androidx.navigation.NavController

fun NavController.navigateReplace(route: String, currentRoute: String) {
    this.navigate(route) {
        popUpTo(currentRoute) { inclusive = true }
    }
}
