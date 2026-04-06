package quang.app.mediator.features.register.viewModels

sealed class RegisterEvent {
    data class EmailChanged(val email: String): RegisterEvent()
    data class PasswordChanged(val password: String): RegisterEvent()
    object TogglePasswordVisibility: RegisterEvent()
    object TogglePrivacyPolicy: RegisterEvent()
    object Submit: RegisterEvent()

}


sealed class RegisterUiEvent{
    object NavigateToHome: RegisterUiEvent()
    data class ShowError(val message: String): RegisterUiEvent()
    data class ShowSuccess(val message: String): RegisterUiEvent()


}
