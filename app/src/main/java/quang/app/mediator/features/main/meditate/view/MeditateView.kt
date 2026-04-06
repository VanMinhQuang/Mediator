package quang.app.mediator.features.main.meditate.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.R
import quang.app.mediator.core.component.CategoryChip
import quang.app.mediator.core.component.MeditationGrid
import quang.app.mediator.core.component.PlayMeditationCard
import quang.app.mediator.core.constants.AppConstants.menuMediates
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.features.main.meditate.viewmodel.MeditateEvent
import quang.app.mediator.features.main.meditate.viewmodel.MeditateViewModel

data class MeditateMenuItem(
    val title: String,
    val icon: Int,
    val isSelected: Boolean = false
)


@Composable
fun MeditateView( viewModel: MeditateViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    val list = state.selectedType?.let { state.meditationsByType[it] } ?: emptyList()

    LaunchedEffect(Unit) {
        viewModel.onEvent(MeditateEvent.LoadMeditations)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColor.White),
        contentAlignment = Alignment.TopCenter,

        ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ){
            Spacer(modifier = Modifier.height(40.dp))

            Text("Meditate", style = AppTextStyle.bold24.copy(fontSize = 26.sp), color = AppColor.TextColor)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "we can learn how to recognize when our minds are doing their normal everyday acrobatics.",
                style = AppTextStyle.light16,
                color = AppColor.TextHint,
                textAlign = TextAlign.Center,
                lineHeight = 25.sp,
            )


            Spacer(modifier = Modifier.height(10.dp))

            CategoryChip(
                menuMediates = menuMediates,
                selectedType = state.selectedType,
                onClick = {
                        menuItem ->
                    viewModel.onEvent(MeditateEvent.SelectMeditationType(menuItem))
                }

            )

            PlayMeditationCard(
                title = "Daily Calm",
                subTitle = "APR 30 • PAUSE PRACTICE",
                backgroundColor = AppColor.LightPeach,
                backgroundImg = R.drawable.daily_calm,
                textColor = AppColor.TextColor,
                isReverseColorButton = true,
                onPlayClick = {

                }
            )



            MeditationGrid(
                list,
                onClick = {

                }
            )

        }
    }
}




@Preview
@Composable
fun MeditateViewPreview() {
    MeditateView()
}
