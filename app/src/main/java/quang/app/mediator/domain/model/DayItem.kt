package quang.app.mediator.domain.model

data class DayItem(
    val label: String,
    val isSelected: Boolean = false
)

val daysOfWeek = listOf(
    DayItem("SU"),
    DayItem("M"),
    DayItem("T"),
    DayItem("W"),
    DayItem("TH"),
    DayItem("F"),
    DayItem("S"),
)
