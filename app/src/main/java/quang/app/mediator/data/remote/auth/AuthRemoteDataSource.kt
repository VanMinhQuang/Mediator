package quang.app.mediator.data.remote.auth

import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.core.supabase.SupabaseService
import quang.app.mediator.data.remote.BaseRemote
import javax.inject.Inject

class AuthRemoteDataSource  @Inject constructor(
    private val provider: SupabaseService
) : AuthRemote, BaseRemote() {
    override suspend fun login(
        email: String,
        password: String
    ): APIResult<Unit>  = safeCall {
        provider.auth.signInWith(Email){
            this.email = email
            this.password = password
        }
    }


    override suspend fun signUp(
        email: String,
        password: String
    ): APIResult<Unit> = safeCall {
        provider.auth.signUpWith(Email){
            this.email = email
            this.password = password
        }
    }

    override suspend fun loginWithGoogle(): APIResult<String> = safeCall {
        val result = provider.auth.signInWith(Google)
        result.toString()
    }

    override suspend fun signOut(): APIResult<Unit> = safeCall {
        provider.auth.signOut()
    }

    override fun getCurrentUser() = provider.auth.currentUserOrNull()


}
