package quang.app.mediator.features.welcome.topic.viewModel

import quang.app.mediator.domain.model.Topic

data class TopicState(
    val topics: List<Topic> = emptyList(),
    val selectedTopicId: String? = null
)
