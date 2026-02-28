package quang.app.mediator.features.welcome.reminder.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import quang.app.mediator.domain.model.daysOfWeek

class ReminderViewModel: ViewModel() {
    private val _state = MutableStateFlow(ReminderState())
    val state = _state.asStateFlow()

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
}
