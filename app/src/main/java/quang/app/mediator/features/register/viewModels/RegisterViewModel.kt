package quang.app.mediator.features.register.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel(){
    private val _state = MutableStateFlow(RegisterState())

    val state = _state.asStateFlow()

    private val _event = Channel<RegisterUiEvent>()
    val event = _event.receiveAsFlow()

    fun onEvent(event: RegisterEvent){
        when(event){
            is RegisterEvent.NameChanged -> {
                _state.value = state.value.copy(userName = event.name)
            }
            is RegisterEvent.EmailChanged -> {
                _state.value = state.value.copy(email = event.email)
            }
            is RegisterEvent.PasswordChanged -> {
                _state.value = state.value.copy(password = event.password)
            }
            is RegisterEvent.TogglePasswordVisibility -> {
                _state.value = state.value.copy(passwordVisible = !state.value.passwordVisible)
            }
            is RegisterEvent.TogglePrivacyPolicy -> {
                _state.value = state.value.copy(isReadPolicy =  !state.value.isReadPolicy)
            }
            is RegisterEvent.Submit -> {
                onRegister()
            }
        }
    }



    fun onRegister(){
        viewModelScope.launch {
            val currentState = state.value

            // Validate
            if (currentState.userName.isBlank() || currentState.email.isBlank() || currentState.password.isBlank()) {
                _event.send(RegisterUiEvent.ShowError("Please fill all fields."))
                return@launch
            }

            _event.send(RegisterUiEvent.ShowLoading)


            delay(2000) // Simulate network request


            _event.send(RegisterUiEvent.HideLoading)

            _event.send(RegisterUiEvent.ShowSuccess("Registration successful!"))
        }
    }

}