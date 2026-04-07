package quang.app.mediator.features.welcome.topic.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import quang.app.mediator.core.component.app.AppStateHolder
import quang.app.mediator.domain.model.UserConfiguration
import quang.app.mediator.domain.model.topics
import javax.inject.Inject


@HiltViewModel
class TopicViewModel @Inject constructor(
    val appStateHolder: AppStateHolder
) : ViewModel() {
    private val _state = MutableStateFlow(TopicState())

    val state = _state.asStateFlow()

    init {
        _state.value = _state.value.copy(topics = topics)
    }

    fun onEvent(event: TopicEvent) {
        when (event) {
            is TopicEvent.TopicSelected -> {
                val updatedTopics = state.value.topics.map { topic ->
                    if (topic.topicId == event.topicId) {
                        topic.copy(isPicked = !topic.isPicked)
                    } else {
                        topic
                    }
                }

                val currentSelectedIds = state.value.selectedTopicIds?.toMutableList() ?: emptyList<String>().toMutableList()

                if (currentSelectedIds.contains(event.topicId)) {
                    currentSelectedIds.remove(event.topicId)
                } else {
                    currentSelectedIds.add(event.topicId)
                }

                _state.value = state.value.copy(
                    topics = updatedTopics,
                    selectedTopicIds = currentSelectedIds
                )
            }
        }
    }


    fun saveSelectedTopic() {
        val currentSelectedIds = state.value.selectedTopicIds
        if (currentSelectedIds != null) {
            appStateHolder.updateUserConfiguration(
                UserConfiguration(
                    userTopics = currentSelectedIds
                )
            )
        }
    }
}
