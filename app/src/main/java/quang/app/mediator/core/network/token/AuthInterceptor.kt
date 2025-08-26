package quang.app.mediator.core.network.token

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor

class AuthInterceptor (
    private val tokenProvider: suspend () -> String?,
    private val onAuthError: suspend () -> Unit
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val requestBuilder = chain.request().newBuilder()

        val token = runBlocking { tokenProvider() }
        token?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        val response = chain.proceed(requestBuilder.build())

        if (response.code == 401 || response.code == 403) {
            runBlocking { onAuthError() }
        }

        return response
    }
}