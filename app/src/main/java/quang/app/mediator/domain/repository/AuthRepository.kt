package quang.app.mediator.domain.repository

import io.github.jan.supabase.auth.user.UserSession
import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.domain.model.User

interface AuthRepository {
    suspend fun authenticate(username: String, password: String): APIResult<Unit>
    suspend fun getCurrentUser(): User
    suspend fun signUp(username: String, password: String): APIResult<Unit>
    suspend fun getCurrentSession(): APIResult<UserSession?>

}
