package quang.app.mediator.features.login.viewModels



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import quang.app.mediator.core.component.app.AppState
import quang.app.mediator.core.component.app.AppStateHolder
import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.domain.repository.AuthRepository
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val appStateHolder: AppStateHolder
) : ViewModel() {


    private val _state = MutableStateFlow(LoginState())

    val state = _state.asStateFlow()
    val appState: StateFlow<AppState> = appStateHolder.state

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

            _state.update {
                it.copy(isLoading = true)
            }


            val result = repository.authenticate(currentState.email, currentState.password)

            when(result){
                is APIResult.Success -> {
                    val user = repository.getCurrentUser()
                    _event.send(LoginUIEvent.ShowSuccess("Login successful! Welcome ${user.username}"))
                }
                is APIResult.Error -> {
                    _event.send(LoginUIEvent.ShowError("Login failed: ${result.error.message ?: "Unknown error"}"))
                }

            }
            _state.update {
                it.copy(isLoading = false)
            }






        }
    }

}
