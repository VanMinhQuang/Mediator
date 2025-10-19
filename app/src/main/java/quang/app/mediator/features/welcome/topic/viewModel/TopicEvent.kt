package quang.app.mediator.features.welcome.topic.viewModel

sealed class TopicEvent {
    data class TopicSelected(val topicId: String): TopicEvent()
}
