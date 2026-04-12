package quang.app.mediator.data.remote.query.topic

import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.data.entity.Topic

interface TopicRemote {
    suspend fun fetchTopicsByType(type: String): APIResult<List<Topic>>

}
