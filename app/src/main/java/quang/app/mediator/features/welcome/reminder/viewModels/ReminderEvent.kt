package quang.app.mediator.features.welcome.reminder.viewModels

import quang.app.mediator.domain.model.DayItem

sealed class ReminderEvent {
    data class TimeSelected(val hour: Int, val minute: Int) : ReminderEvent()
    data class DayToggled(val day: DayItem) : ReminderEvent()
}
