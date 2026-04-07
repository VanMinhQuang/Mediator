package quang.app.mediator.domain.repository

import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.domain.model.UserConfiguration

interface UserRepository {
        suspend fun getUserConfiguration(userId: String): APIResult<UserConfiguration>
        suspend fun saveUserConfiguration(config: UserConfiguration): APIResult<Unit>
}
