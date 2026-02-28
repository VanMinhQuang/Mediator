package quang.app.mediator.data.repository

import quang.app.mediator.core.network.NetworkService
import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.core.network.results.NetworkError
import quang.app.mediator.data.remote.AuthApi
import quang.app.mediator.data.remote.models.AuthRequest
import quang.app.mediator.domain.model.User
import quang.app.mediator.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val networkService: NetworkService
): AuthRepository  {
    override suspend fun authenticate(
        username: String,
        password: String
    ): APIResult<User> {
        try{
            val request = AuthRequest(username, password)
            val response = networkService.execute {
                authApi.authentication(request)
            }

            return when(response){
                is APIResult.Success -> APIResult.Success(response.data)
                is APIResult.Error -> APIResult.Error(response.error)
            }
        }catch (e: Exception){
            return APIResult.Error(NetworkError.Unknown(
                e.message ?: "Unknown error"
            ));
        }
    }
}
