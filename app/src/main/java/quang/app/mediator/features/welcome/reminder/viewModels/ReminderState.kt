package quang.app.mediator.features.welcome.reminder.viewModels

import quang.app.mediator.data.model.DayItem

data class ReminderState(
    val selectedHour: Int = 6,
    val selectedMinute: Int = 0,
    val days: List<DayItem> = emptyList()
)
