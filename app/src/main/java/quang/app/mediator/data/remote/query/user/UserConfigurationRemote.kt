package quang.app.mediator.data.remote.query.user

import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.domain.model.UserConfiguration

interface UserConfigurationRemote {
    suspend fun fetchByUserId(userId: String): APIResult<UserConfiguration>
    suspend fun insert(config: UserConfiguration): APIResult<Unit>
    suspend fun upsert(config: UserConfiguration): APIResult<Unit>
    suspend fun update(userId: String, update: UserConfiguration): APIResult<Unit>
    suspend fun delete(userId: String): APIResult<Unit>
    suspend fun fetchAll(): APIResult<List<UserConfiguration>>


}
