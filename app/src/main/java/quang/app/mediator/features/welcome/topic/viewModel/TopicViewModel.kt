package quang.app.mediator.features.welcome.topic.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import quang.app.mediator.core.component.app.AppStateHolder
import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.domain.model.UserConfiguration
import quang.app.mediator.domain.repository.TopicRepository
import javax.inject.Inject


@HiltViewModel
class TopicViewModel @Inject constructor(
    val appStateHolder: AppStateHolder,
    val repository: TopicRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TopicState())

    val state = _state.asStateFlow()

    private val _event = Channel<TopicUIEvent>()

    val event = _event.receiveAsFlow()


    init {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val topics = repository.fetchTopicsByType("WELCOME")
            when (topics) {
                is APIResult.Success -> {
                    _state.value = TopicState(
                        topics = topics.data
                    )
                }
                is APIResult.Error -> {
                    _event.send(TopicUIEvent.ShowError(topics.error.message ?: "Unknown error"))
                }
            }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun onEvent(event: TopicEvent) {
        when (event) {
            is TopicEvent.TopicSelected -> {
                val updatedTopics = state.value.topics.map { topic ->
                    if (topic.id == event.topicId) {
                        topic.copy(isPicked = !topic.isPicked)
                    } else {
                        topic
                    }
                }

                val currentSelectedIds = state.value.selectedTopicIds
                    ?.toMutableList()
                    ?: mutableListOf()

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
