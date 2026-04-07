package quang.app.mediator.data.repository

import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.core.network.results.NetworkError
import quang.app.mediator.data.remote.query.user.UserConfigurationRemote
import quang.app.mediator.domain.model.UserConfiguration
import quang.app.mediator.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remote: UserConfigurationRemote
) : UserRepository {
    override suspend fun getUserConfiguration(userId: String): APIResult<UserConfiguration> {
        return try {
             remote.fetchByUserId(userId)
        } catch (e: NetworkError) {
            APIResult.Error(e) // wrap NetworkError safely
        }
    }

    override suspend fun saveUserConfiguration(config: UserConfiguration): APIResult<Unit> {
        return try {
            remote.insert(config)
        } catch (e: NetworkError) {
            APIResult.Error(e) // wrap NetworkError safely
        }
    }
}
