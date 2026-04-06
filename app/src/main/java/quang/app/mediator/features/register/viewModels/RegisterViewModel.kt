package quang.app.mediator.features.register.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel(){
    private val _state = MutableStateFlow(RegisterState())

    val state = _state.asStateFlow()


    private val _event = Channel<RegisterUiEvent>()
    val event = _event.receiveAsFlow()

    val isEnabled: Boolean
        get() {
            val currentState = state.value
            return currentState.email.isNotBlank() &&
                    currentState.password.isNotBlank() &&
                    currentState.isReadPolicy
        }

    fun onEvent(event: RegisterEvent){
        when(event){

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
            if ( currentState.email.isBlank() || currentState.password.isBlank()) {
                _event.send(RegisterUiEvent.ShowError("Please fill all fields."))
                return@launch
            }

            _state.value = currentState.copy(isLoading = true)


            val result =  repository.signUp(currentState.email, currentState.password)


            _state.value = currentState.copy(isLoading = false)

            when(result){
                is APIResult.Success -> {
                    _event.send(RegisterUiEvent.ShowSuccess("Registration successful!"))

                }
                is APIResult.Error -> {
                    _event.send(RegisterUiEvent.ShowError(result.error.message ?: "Registration failed: Unknown error"))

                }
            }
        }
    }




}
