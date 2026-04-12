package quang.app.mediator.features.welcome.topic.viewModel

sealed class TopicEvent {
    data class TopicSelected(val topicId: Int): TopicEvent()
}

sealed class TopicUIEvent {
    data class ShowError(val message: String): TopicUIEvent()
}
