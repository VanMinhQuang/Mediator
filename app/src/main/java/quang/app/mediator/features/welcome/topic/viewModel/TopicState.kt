package quang.app.mediator.features.welcome.topic.viewModel

import quang.app.mediator.domain.model.MediationTopic

data class TopicState(
    val topics: List<MediationTopic> = emptyList(),
    val selectedTopicIds: List<Int>? = null,
    val isLoading: Boolean = false
)
