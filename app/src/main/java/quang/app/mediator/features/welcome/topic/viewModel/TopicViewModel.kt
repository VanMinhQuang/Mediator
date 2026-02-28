package quang.app.mediator.features.welcome.topic.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import quang.app.mediator.domain.model.topics


class TopicViewModel : ViewModel() {
    private val _state = MutableStateFlow(TopicState())

    val state = _state.asStateFlow()

    init {
        _state.value = _state.value.copy(topics = topics)
    }

    fun onEvent(event: TopicEvent) {
        when (event) {
            is TopicEvent.TopicSelected -> {
                val updatedTopics = state.value.topics.map { topic ->
                    when (topic.topicId) {
                        event.topicId -> topic.copy(isPicked = !topic.isPicked)
                        else -> if (topic.isPicked) topic.copy(isPicked = false) else topic
                    }
                }
                val selectedTopicId = updatedTopics.find { it.isPicked }?.topicId
                _state.value =
                    state.value.copy(topics = updatedTopics, selectedTopicId = selectedTopicId)
            }
        }
    }
}
