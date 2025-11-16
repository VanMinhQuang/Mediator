package quang.app.mediator.features.main.meditate.view

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.R
import quang.app.mediator.core.component.PlayMeditationCard
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.data.model.MeditationModel
import quang.app.mediator.features.main.meditate.viewmodel.MeditateEvent
import quang.app.mediator.features.main.meditate.viewmodel.MeditateViewModel

data class MeditateMenuItem(
    val title: String,
    val icon: Int,
    val isSelected: Boolean = false
)

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MeditationGrid(items: List<MeditationModel>, onClick: (MeditationModel) -> Unit) {
    val shortHeight = 200.dp
    val longHeight = 280.dp

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 12.dp
    ) {
        items(items.size) { index ->
            val topic = items[index]
            // Detect column (0 or 1)
            val column = index % 2

            // Column 0: short-long-short-long
            // Column 1: long-short-long-short
            val height =
                if (column == 0) {
                    if ((index / 2) % 2 == 0) shortHeight else longHeight
                } else {
                    if ((index / 2) % 2 == 0) longHeight else shortHeight
                }
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .clickable { onClick(topic) }
                    .fillMaxWidth()
                    .wrapContentHeight(),
                elevation = CardDefaults.cardElevation(6.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    // Image as background
                    AsyncImage(
                        model = topic.imageUrl ?: "",
                        contentDescription = "Test",
                        error = painterResource(id = R.drawable.meditate),
                        contentScale = ContentScale.FillBounds,
                        placeholder = painterResource(id = R.drawable.meditate),
                        onError = {
                                error ->
                            Log.e("CoilImage", "Failed to load: ${error.result.throwable}")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height)
                            .clip(RoundedCornerShape(16.dp))
                    )


                    Text(
                        text = topic.title ?: "",
                        style = AppTextStyle.semiBold16.copy(
                            color = AppColor.White,          // Ensure text is visible on image

                        ),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(12.dp)
                    )
                }
            }
        }

    }
}


@Composable
fun CategoryChip(
    menuMediates: List<MeditateMenuItem>,
    selectedType: MeditateMenuItem?,
    onClick: (MeditateMenuItem) -> Unit = {}
){


    LazyRow(
        modifier = Modifier.fillMaxWidth(),

        ) {
        items(menuMediates) {
                item ->
            val isSelected = item == selectedType
            val backgroundColor = if(isSelected) AppColor.Primary else AppColor.Gray700
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)

            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(backgroundColor)
                        .clickable { onClick(item) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = AppColor.White,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.title,
                    color = if(item.isSelected) AppColor.TextColor else AppColor.Gray700,
                    style = AppTextStyle.regular14
                )
            }
        }
    }



}

@Preview
@Composable
fun MeditateViewPreview() {
    MeditateView()
}
