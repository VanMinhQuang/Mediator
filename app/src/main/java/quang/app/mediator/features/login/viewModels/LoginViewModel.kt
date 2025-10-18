package quang.app.mediator.features.login.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())

    val state = _state.asStateFlow()

    private val _event = Channel<LoginUIEvent>()

    val event = _event.receiveAsFlow()


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnterEmail -> {
                _state.value = state.value.copy(email = event.email)
            }
            is LoginEvent.EnterPassword -> {
                _state.value = state.value.copy(password = event.password)
            }
            is LoginEvent.TogglePasswordVisibility -> {
                _state.value = state.value.copy(passwordVisible = !state.value.passwordVisible)
            }
            is LoginEvent.SubmitLogin -> {
                onLogin()
            }
        }
    }


    fun onLogin(){
        viewModelScope.launch {
            val currentState = state.value

            // Validate
            if (currentState.email.isBlank() ||currentState.password.isBlank()) {
                _event.send(LoginUIEvent.ShowError("Please fill all fields."))
                return@launch
            }

            _event.send(LoginUIEvent.ShowLoading)


            delay(2000) // Simulate network request


            _event.send(LoginUIEvent.HideLoading)

            _event.send(LoginUIEvent.ShowSuccess("Registration successful!"))
        }
    }
}