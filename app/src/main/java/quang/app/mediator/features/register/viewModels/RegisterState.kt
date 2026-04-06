package quang.app.mediator.features.register.viewModels

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isReadPolicy: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false
)
