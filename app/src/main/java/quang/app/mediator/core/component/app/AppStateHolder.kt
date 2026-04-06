package quang.app.mediator.core.component.app

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import quang.app.mediator.domain.model.User
import javax.inject.Inject
import javax.inject.Singleton

// ── User Status ──────────────────────────────────────────────────────────────
enum class UserStatus {
    LOADING,        // Checking session on app start
    AUTHENTICATED,  // Logged in
    UNAUTHENTICATED // No session
}


// ── App State ─────────────────────────────────────────────────────────────────
data class AppState(
    val userStatus: UserStatus = UserStatus.LOADING,
    val userInfo: User? = null
)

// ── App State Holder ──────────────────────────────────────────────────────────
@Singleton
class AppStateHolder @Inject constructor() {

    private val _state = MutableStateFlow(AppState())
    val state: StateFlow<AppState> = _state.asStateFlow()

    fun setAuthenticated(userInfo: User) {
        _state.update {
            it.copy(
                userStatus = UserStatus.AUTHENTICATED,
                userInfo = userInfo
            )
        }
    }

    fun setUnauthenticated() {
        _state.update {
            it.copy(
                userStatus = UserStatus.UNAUTHENTICATED,
                userInfo = null
            )
        }
    }

    fun setLoading() {
        _state.update {
            it.copy(userStatus = UserStatus.LOADING)
        }
    }
}
