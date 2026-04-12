package quang.app.mediator.domain.repository

import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.domain.model.MediationTopic

interface TopicRepository {
    suspend fun fetchTopicsByType(type: String): APIResult<List<MediationTopic>>
}
