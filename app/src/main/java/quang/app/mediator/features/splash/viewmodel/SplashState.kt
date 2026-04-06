package quang.app.mediator.features.splash.viewmodel

import quang.app.mediator.core.component.app.UserStatus

data class SplashState (
    val userStatus: UserStatus = UserStatus.LOADING
)
