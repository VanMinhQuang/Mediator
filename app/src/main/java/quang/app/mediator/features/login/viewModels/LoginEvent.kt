package quang.app.mediator.features.login.viewModels

sealed class LoginEvent{
    data class EnterEmail(val email: String) : LoginEvent()
    data class EnterPassword(val password: String) : LoginEvent()
    object TogglePasswordVisibility : LoginEvent()
    object SubmitLogin : LoginEvent()
}


sealed class LoginUIEvent{

    data class ShowError(val message: String): LoginUIEvent()
    data class ShowSuccess(val message: String): LoginUIEvent()


}
