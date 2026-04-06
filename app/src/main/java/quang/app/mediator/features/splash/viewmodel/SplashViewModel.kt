package quang.app.mediator.features.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import quang.app.mediator.core.component.app.AppStateHolder
import quang.app.mediator.core.component.app.UserStatus
import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.domain.repository.AuthRepository
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    val repository: AuthRepository,
    val appState: AppStateHolder
) : ViewModel() {
    private val _state = MutableStateFlow(SplashState())

    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            val session = repository.getCurrentSession()
            delay(2000)

            when(session){
                is APIResult.Success -> {
                    if (session.data != null) {
                        try {
                            val user = repository.getCurrentUser()
                            appState.setAuthenticated(user)
                            _state.value = _state.value.copy(userStatus = UserStatus.AUTHENTICATED)
                        } catch (e: Exception) {
                            _state.value = _state.value.copy(userStatus = UserStatus.UNAUTHENTICATED)
                        }
                    } else {
                        _state.value = _state.value.copy(userStatus = UserStatus.UNAUTHENTICATED)
                    }
                }
                is APIResult.Error -> {
                    _state.value = _state.value.copy(userStatus = UserStatus.UNAUTHENTICATED)
                }
            }

        }
    }
}
