package quang.app.mediator.features.welcome.reminder.viewModels

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
import quang.app.mediator.domain.model.UserConfiguration
import quang.app.mediator.domain.model.daysOfWeek
import quang.app.mediator.domain.repository.UserRepository
import javax.inject.Inject


@HiltViewModel
class ReminderViewModel @Inject constructor(
    val appStateHolder: AppStateHolder,
    val repository: UserRepository
): ViewModel() {
    private val _state = MutableStateFlow(ReminderState())
    private val _event = Channel<ReminderUIEvent>()
    val state = _state.asStateFlow()
    val event = _event.receiveAsFlow()

    init {
        _state.value = _state.value.copy(
            days = daysOfWeek
        )
    }

    fun onEvent(event: ReminderEvent) {
        when (event) {
            is ReminderEvent.TimeSelected -> {
                _state.value = _state.value.copy(
                    selectedHour = event.hour,
                    selectedMinute = event.minute
                )
            }
            is ReminderEvent.DayToggled -> {
                val currentDays = _state.value.days

                val updatedDays = currentDays.map { day ->
                    if (day.label == event.day.label) {
                        day.copy(isSelected = !day.isSelected)
                    } else {
                        day
                    }
                }

                _state.value = _state.value.copy(days = updatedDays)
            }
        }
    }


    fun saveSettings(isSkip: Boolean){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            appStateHolder.updateUserConfiguration(
                UserConfiguration(
                    userId = appStateHolder.state.value.userInfo?.id,
                    meditateTime = if(isSkip) null else "${state.value.selectedHour}:${state.value.selectedMinute}",
                    meditateDays = if(isSkip) null else state.value.days.filter { it.isSelected }.map { it.label },
                    finishWelcome =  true
                )
            )

            if(appStateHolder.state.value.userConfiguration != null){
                repository.saveUserConfiguration(appStateHolder.state.value.userConfiguration!!)

            }

            _state.update {
                it.copy(isLoading = false)
            }

            _event.send(ReminderUIEvent.NavigateToHome)
        }
    }
}
