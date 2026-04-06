package quang.app.mediator.data.remote

import io.github.jan.supabase.exceptions.RestException
import kotlinx.coroutines.withTimeout
import kotlinx.io.IOException
import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.core.network.results.NetworkError
import java.net.SocketTimeoutException

abstract class BaseRemote(
    private val defaultTimeout: Long = 20_000L

){
     suspend fun <T> safeCall(
        call: suspend () -> T
    ): APIResult<T> {
        return try {
            withTimeout(defaultTimeout) {
                APIResult.Success(call())
            }
        } catch (e: Exception) {
            handleError(e)
        }
    }

    private fun handleError(e: Exception): APIResult<Nothing> {
        throw when (e) {
            is SocketTimeoutException -> NetworkError.Timeout
            is IOException -> NetworkError.Unknown("Network error: ${e.localizedMessage}")
            is RestException -> {
                when (e.statusCode) {
                    401 -> NetworkError.Unauthorized
                    404 -> NetworkError.NotFound
                    else -> NetworkError.Unknown(e.message ?: "Database error")
                }
            }
            else -> NetworkError.Unknown(e.localizedMessage ?: "Unknown error")
        }
    }
}
