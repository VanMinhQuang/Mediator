package quang.app.mediator.core.network

import kotlinx.coroutines.withTimeout
import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.core.network.results.NetworkError
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class NetworkService @Inject constructor ( private val defaultTimeout: Long = 20_000L) {



    suspend fun <T> execute(call: suspend () -> Response<T>): APIResult<T> {
        return try {
            withTimeout(defaultTimeout){
                val response = call()
                if (response.isSuccessful) {
                    response.body()?.let { APIResult.Success(it) }
                        ?: APIResult.Error(NetworkError.Unknown("Unknown error"))
                } else {
                    when (response.code()) {
                        401 -> APIResult.Error(NetworkError.Unauthorized)
                        404 -> APIResult.Error(NetworkError.NotFound)
                        else -> APIResult.Error(NetworkError.Unknown(response.message()))
                    }
                }
            }

        } catch (e: SocketTimeoutException) {
            APIResult.Error(NetworkError.Timeout)
        } catch (e: IOException) {
            APIResult.Error(NetworkError.Unknown("Network error"))
        } catch (e: Exception) {
            APIResult.Error(NetworkError.Unknown(e.message ?: "Unknown error"))
        }
    }
}
