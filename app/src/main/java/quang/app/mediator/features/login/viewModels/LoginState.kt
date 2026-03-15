package quang.app.mediator.features.login.viewModels

data class LoginState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean  = false
)
