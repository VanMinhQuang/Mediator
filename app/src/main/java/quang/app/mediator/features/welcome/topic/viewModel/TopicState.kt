package quang.app.mediator.features.welcome.topic.viewModel

import quang.app.mediator.data.model.Topic

data class TopicState(
    val topics: List<Topic> = emptyList(),
    val selectedTopic: Topic? = null
)
