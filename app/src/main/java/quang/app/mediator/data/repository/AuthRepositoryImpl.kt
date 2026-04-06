package quang.app.mediator.data.repository


import io.github.jan.supabase.auth.user.UserSession
import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.core.network.results.NetworkError
import quang.app.mediator.data.remote.auth.AuthRemote
import quang.app.mediator.domain.model.User
import quang.app.mediator.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remote: AuthRemote
) : AuthRepository {
    override suspend fun authenticate(
        username: String,
        password: String
    ): APIResult<Unit> {
        return try {
            remote.login(username, password) // may throw NetworkError
            APIResult.Success(Unit)
        } catch (e: NetworkError) {
            APIResult.Error(e) // wrap NetworkError safely
        }
    }

    override suspend fun getCurrentUser(): User {
        try {
            val result = remote.getCurrentUser()
            return User.fromUserInfo(result)
        } catch (e: NetworkError) {
            return User.fromUserInfo(null)
        }
    }

    override suspend fun signUp(
        username: String,
        password: String
    ): APIResult<Unit> {
        try{
            remote.signUp(username, password)
            return APIResult.Success(Unit)
        } catch (e: NetworkError) {
            return APIResult.Error(
                e
            )
        }
    }

    override suspend  fun getCurrentSession(): APIResult<UserSession?> {
        return try{
            remote.getCurrentSession()
        } catch (e: NetworkError) {
             APIResult.Error(
                e
            )
        }
    }
}
