package quang.app.mediator.data.remote.query.topic

import io.github.jan.supabase.postgrest.query.Order
import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.data.entity.Topic
import quang.app.mediator.data.remote.query.PostgrestRemoteDataSource
import javax.inject.Inject

class TopicRemoteDataSource @Inject constructor(
    private val postgrest: PostgrestRemoteDataSource
): TopicRemote {
    private val table = "topic"
    override suspend fun fetchTopicsByType(type: String): APIResult<List<Topic>> {
        return postgrest.fetchList(table = table) {
            filter {
                eq("topic_type",type)
            }
            order(column = "id", order = Order.ASCENDING)
        }
    }


}
