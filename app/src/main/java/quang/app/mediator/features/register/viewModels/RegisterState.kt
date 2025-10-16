package quang.app.mediator.features.register.viewModels

data class RegisterState(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isReadPolicy: Boolean = false,
    val error: String? = null
)


