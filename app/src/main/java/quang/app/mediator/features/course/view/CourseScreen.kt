package quang.app.mediator.features.course.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.sharp.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yourapp.ui.theme.AppTextStyle
import kotlinx.coroutines.launch
import quang.app.mediator.R
import quang.app.mediator.core.component.AppImage
import quang.app.mediator.core.component.CircularBackButton
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.domain.model.MeditationModel
import quang.app.mediator.domain.model.Music
import quang.app.mediator.features.course.components.NarratorContentList

enum class Narrator(val title: String){
    MALE("MALE VOICE"),
    FEMALE("FEMALE VOICE"),

}
@Composable
fun CourseScreen(navController: NavController){
    val topic = MeditationModel.dummyList()[0]

    val startDestination = Narrator.MALE
    val pagerState = rememberPagerState(pageCount = { 2})
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom)

    ) {
        innerPadding -> Box(
            modifier = Modifier.padding(innerPadding)
        ){
            Column(
                horizontalAlignment = Alignment.Start,

            ) {
                Box{
                    AppImage(
                        url = topic.imageUrl ?: "",
                        placeholder =  R.drawable.sunny,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(290.dp)
                            .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)),

                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp)
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularBackButton(
                            onBack = { navController.popBackStack() },
                            borderColor = AppColor.Gray
                        )


                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .size(48.dp)
                                    .border(
                                        width = 1.dp,
                                        shape = CircleShape,
                                        color = Color.Transparent
                                    )
                                    .clip(CircleShape)
                                    .background(AppColor.TextColor.copy(alpha = 0.3f)) // apply opacity here
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.FavoriteBorder,
                                    contentDescription = "Favorite",
                                    tint = AppColor.White
                                )
                            }


                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .size(48.dp)
                                    .border(
                                        width = 1.dp,
                                        shape = CircleShape,
                                        color = Color.Transparent
                                    )
                                    .clip(CircleShape)
                                    .background(AppColor.TextColor.copy(alpha = 0.3f))

                            ) {
                                Icon(
                                    imageVector = Icons.Sharp.Download,
                                    contentDescription = "Download",
                                    tint = AppColor.White
                                )
                            }


                        }
                    }

                }

                Column (

                    modifier = Modifier
                        .padding(vertical = 15.dp, horizontal = 20.dp)
                        .fillMaxWidth()
                ){
                    Text(
                        text = topic.title ?: "",
                        style = AppTextStyle.bold32,
                        color = AppColor.TextColor,
                        modifier = Modifier.padding(vertical = 15.dp)
                    )
                    Text(
                        text = "COURSE",
                        style = AppTextStyle.semiBold16.copy(color = AppColor.TextHint),
                        modifier = Modifier.padding(bottom = 15.dp)
                    )

                    Text(
                        text = topic.subTitle ?: "",
                        style = AppTextStyle.light16.copy(color = AppColor.TextHint),
                        modifier = Modifier.padding(bottom = 20.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ){
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically

                        ){
                            Icon(
                                painter = painterResource(id = R.drawable.heart_icon),
                                contentDescription = "Favorite",
                                tint = AppColor.LightPink,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text =  "24.234 Favorites",
                                style = AppTextStyle.regular14.copy(color = AppColor.TextHint),
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                            ){
                            Icon(
                                painter = painterResource(id = R.drawable.music),
                                contentDescription = "Listen",
                                tint = AppColor.LightBlue,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text =  "34.234 Listening",
                                style = AppTextStyle.regular14.copy(color = AppColor.TextHint),
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }

                    Text(
                        text = "Pick a Narrator",
                        style = AppTextStyle.bold22,
                        color = AppColor.TextColor,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )




                }

                PrimaryTabRow(selectedTabIndex = pagerState.currentPage) {
                    Narrator.entries.forEachIndexed { index, destination ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            text = {
                                Text(
                                    text = destination.title,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        )
                    }
                }

                // 4. Add the HorizontalPager to show different lists
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), // weight(1f) fills remaining space
                    verticalAlignment = Alignment.Top
                ) { pageIndex ->

                    // 5. Call a function to show the list based on the narrator
                    NarratorContentList(musics = Music.dummyList())
                }

            }


        }

    }
}

@Preview
@Composable
fun CourseScreenPreview() {
    CourseScreen(navController = rememberNavController())
}
