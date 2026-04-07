package quang.app.mediator.data.remote.query.user

import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.data.remote.query.PostgrestRemoteDataSource
import quang.app.mediator.domain.model.UserConfiguration
import javax.inject.Inject

class UserConfigurationRemoteDataSource @Inject constructor(
    private val postgrest: PostgrestRemoteDataSource
): UserConfigurationRemote {

    private val table = "user_configurations"

    // Fetch single by userId
    override suspend fun fetchByUserId(userId: String): APIResult<UserConfiguration> {
        return postgrest.fetchSingle(table = table) {
            filter {
                eq("user_id", userId)
            }
        }
    }

    // Fetch all configs (rarely used)
    override suspend fun fetchAll(): APIResult<List<UserConfiguration>> {
        return postgrest.fetchList(table = table)
    }

    // Insert new configuration
    override suspend fun insert(config: UserConfiguration): APIResult<Unit> {
        return postgrest.safeCall {
            postgrest.supabaseService.postgrest[table]
                .insert(config)

        }
    }

    // Upsert (insert or update based on userId primary key)
    override suspend fun upsert(config: UserConfiguration): APIResult<Unit> {
        return postgrest.safeCall {
            postgrest.supabaseService.postgrest[table]
                .upsert(config)
        }
    }

    // Update existing configuration
    override suspend fun update(userId: String, update: UserConfiguration): APIResult<Unit> {
        return postgrest.safeCall {
            postgrest.supabaseService.postgrest[table]
                .update(update){
                    filter {
                        eq("user_id", userId)
                    }
                }
        }
    }

    // Delete configuration
    override suspend fun delete(userId: String): APIResult<Unit> {
        return postgrest.safeCall {
            postgrest.supabaseService.postgrest[table]
                .delete(){
                    filter {
                        eq("user_id", userId)
                    }
                }
        }
    }
}
