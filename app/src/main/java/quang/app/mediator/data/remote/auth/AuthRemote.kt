package quang.app.mediator.data.remote.auth

import io.github.jan.supabase.auth.user.UserInfo
import quang.app.mediator.core.network.results.APIResult

interface AuthRemote {
    suspend fun login(email: String, password: String): APIResult<Unit>
    suspend fun signUp(email: String, password: String): APIResult<Unit>
    suspend fun loginWithGoogle(): APIResult<String>
    suspend fun signOut(): APIResult<Unit>
    fun getCurrentUser(): UserInfo?
}
