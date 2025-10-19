package quang.app.mediator.data.model

import androidx.compose.ui.graphics.Color
import quang.app.mediator.R
import quang.app.mediator.core.styles.AppColor

data class Topic(
    val topicId: String,
    val title: String,
    val color: Color,
    val secondColor: Color? = null,
    val textColor: Color,
    val image: Int,
    val isPicked: Boolean
)

val topics = listOf(
    Topic(
        topicId = "1",
        title = "Reduce Stress",
        color = AppColor.BluePurple,
        secondColor = AppColor.Secondary,
        image = R.drawable.meditating,
        textColor = AppColor.TextCream,
        isPicked = false
    ),
    Topic(
        topicId = "2",
        title = "Improve Performance",
        color = AppColor.CarrotOrange,
        textColor = AppColor.White,
        image = R.drawable.working,
        isPicked = false
    ),
    Topic(
        topicId = "3",
        title = "Increase Happiness",
        color = AppColor.LightOrange,
        textColor = AppColor.TextColor,
        image = R.drawable.smiling_group,
        isPicked = false
    ),
    Topic(
        topicId = "4",
        title = "Reduce Anxiety",
        color = AppColor.BrightYellow,
        textColor = AppColor.TextColor,
        image = R.drawable.anxiety,
        isPicked = false
    ),
    Topic(
        topicId = "5",
        title = "Personal Growth",
        color = AppColor.DarkGreen,
        textColor = AppColor.TextCream,
        image = R.drawable.growth,
        isPicked = false
    ),
    Topic(
        topicId = "6",
        title = "Better Sleep",
        color = AppColor.DarkGray,
        secondColor = AppColor.BrightBlack,
        textColor = AppColor.White,
        image = R.drawable.sleep_person,
        isPicked = false
    ),
    Topic(
        topicId = "7",
        title = "Focus On Your Life",
        color = AppColor.DarkBlue,
        textColor = AppColor.White,
        image = R.drawable.meditating,
        isPicked = false
    ),
    Topic(
        topicId = "8",
        title = "Time For Yourself",
        color = AppColor.DarkPink,
        textColor = AppColor.TextCream,
        image = R.drawable.working_pink,
        isPicked = false
    ),
)
