package quang.app.mediator.data.remote

import quang.app.mediator.data.remote.models.AuthRequest
import quang.app.mediator.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/login")
    suspend fun authentication(@Body request: AuthRequest): Response<User>

}
