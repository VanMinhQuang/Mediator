package quang.app.mediator.features.welcome.reminder.view

import AppButton
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.core.component.TimePicker
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.domain.model.DayItem
import quang.app.mediator.core.navigation.Routes
import quang.app.mediator.features.welcome.reminder.viewModels.ReminderEvent
import quang.app.mediator.features.welcome.reminder.viewModels.ReminderViewModel

@Composable
fun ReminderScreen(navController: NavController, viewModel: ReminderViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColor.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                "What time would you \nlike to meditate?",
                style = AppTextStyle.semiBold24.copy(lineHeight = 35.sp),
                color = AppColor.TextColor,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Any time you can choose but We recommend first thing in the morning.",
                style = AppTextStyle.light18,
                color = AppColor.TextHint,
                textAlign = TextAlign.Left,

                )

            TimePicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 35.dp),
                rowCount = 3,
                onTimeSelected = { hour, minute ->
                    viewModel.onEvent(
                        ReminderEvent.TimeSelected(
                            hour,
                            minute
                        )
                    )
                }
            )

            Text(
                "Which day would you \nlike to meditate?",
                style = AppTextStyle.semiBold24.copy(lineHeight = 35.sp),
                color = AppColor.TextColor,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Everyday is best, but we recommend picking at least five.",
                style = AppTextStyle.light18,
                color = AppColor.TextHint,
                textAlign = TextAlign.Left,

                )


            DaySelectorList(days = state.days, onDayClick = { dayItem ->
                viewModel.onEvent(ReminderEvent.DayToggled(dayItem))
            })

            AppButton(
                text = "SAVE",
                gradient = AppColor.PrimaryGradient,
                textStyle = AppTextStyle.semiBold14,
                textColor = AppColor.White,
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxSize(),
                onTap = {
                    navController.navigate(Routes.HOME)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            AppButton(
                text = "NO THANKS",
                color = Color.Transparent,
                textStyle = AppTextStyle.semiBold14,
                textColor = AppColor.TextColor,
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxSize(),
                onTap = {
                    navController.navigate(Routes.HOME)
                }
            )

        }
    }
}


@Composable
fun DaySelectorList(
    days: List<DayItem>,
    onDayClick: (DayItem) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(days.size) { index ->
            val day = days[index]

            // Animate background color
            val backgroundColor by animateColorAsState(
                targetValue = if (day.isSelected) AppColor.TextColor else Color.Transparent,
                animationSpec = tween(durationMillis = 300),
                label = "dayColorAnimation"
            )

            // Animate text color too (optional)
            val textColor by animateColorAsState(
                targetValue = if (day.isSelected) Color.White else AppColor.TextHint,
                animationSpec = tween(durationMillis = 300),
                label = "textColorAnimation"
            )

            Surface(
                onClick = { onDayClick(day) },
                shape = CircleShape,
                color = backgroundColor,
                border = BorderStroke(1.dp, AppColor.TextHint),
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = day.label,
                        color = textColor,
                        style = AppTextStyle.medium14
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ReminderScreenPreview() {
    ReminderScreen(navController = rememberNavController())
}
