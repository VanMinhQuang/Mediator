package quang.app.mediator.domain.repository

import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.domain.model.User

interface AuthRepository {
    suspend fun authenticate(username: String, password: String): APIResult<User>
}
