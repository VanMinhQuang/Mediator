package quang.app.mediator.core.constants

import quang.app.mediator.R
import quang.app.mediator.features.main.meditate.view.MeditateMenuItem

object AppConstants {
    val menuMediates = listOf<MeditateMenuItem>(
        MeditateMenuItem(
            title = "All",
            icon = R.drawable.all_icon,
            isSelected = false,
        ),
        MeditateMenuItem(
            title = "My",
            icon = R.drawable.heart_icon,
            isSelected = true,
        ),
        MeditateMenuItem(
            title = "Anxious",
            icon = R.drawable.sad_icon,
            isSelected = false,
        ),
        MeditateMenuItem(
            title = "Sleep",
            icon = R.drawable.sleep_icon,
            isSelected = false,
        ),
        MeditateMenuItem(
            title = "Kids",
            icon = R.drawable.kid_icon,
            isSelected = false,
        )
    )

}
