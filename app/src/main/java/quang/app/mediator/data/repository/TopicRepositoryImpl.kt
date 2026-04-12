package quang.app.mediator.data.repository

import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.core.network.results.NetworkError
import quang.app.mediator.data.remote.query.topic.TopicRemote
import quang.app.mediator.domain.model.MediationTopic
import quang.app.mediator.domain.repository.TopicRepository
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    val remote: TopicRemote
) : TopicRepository {
    override suspend fun fetchTopicsByType(type: String): APIResult<List<MediationTopic>> {
        return try{
            val result = remote.fetchTopicsByType(type)

            when(result){
                is APIResult.Success -> {
                    val topics = result.data.map { topic ->
                        MediationTopic.fromEntity(topic)
                    }
                    APIResult.Success(topics)
                }
                is APIResult.Error -> {
                    throw(result.error)
                }
            }
        }catch (e: NetworkError){
             APIResult.Error(e)
        }

    }
}
