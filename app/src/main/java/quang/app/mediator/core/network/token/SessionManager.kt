package quang.app.mediator.core.network.token

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object SessionManager {
    private val _sessionExpired = MutableSharedFlow<Unit>()
    val sessionExpired = _sessionExpired.asSharedFlow()

    suspend fun notifySessionExpired() {
        _sessionExpired.emit(Unit)
    }
}