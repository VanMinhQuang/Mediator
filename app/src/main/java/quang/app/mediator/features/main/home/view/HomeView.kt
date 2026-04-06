package quang.app.mediator.features.main.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.R
import quang.app.mediator.core.component.CircularPlayButton
import quang.app.mediator.core.component.PlayMeditationCard
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.domain.model.Topic
import quang.app.mediator.domain.model.homeTopics
import quang.app.mediator.domain.model.recommendedTopics
import quang.app.mediator.core.navigation.Routes


@Composable
fun HomeView(navController: NavController) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColor.White)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                ),

            ) {
            Icon(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Logo",
                tint = Color.Unspecified,
                modifier = Modifier
                    .width(180.dp)
                    .height(120.dp)
                    .align(Alignment.CenterHorizontally)
            )



            Text(
                "Good Morning, Quang",
                style = AppTextStyle.semiBold28.copy(lineHeight = 45.sp),
                color = AppColor.TextColor,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "We wish you have a good day",
                style = AppTextStyle.light20,
                color = AppColor.TextHint,
                textAlign = TextAlign.Left,

                )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                MediatingHomeCard(
                    modifier = Modifier
                        .weight(1f)
                        .height(200.dp).clickable(onClick = {
                            navController.navigate(Routes.COURSE)
                        }),
                    topic = homeTopics[0],
                    boxColor = AppColor.WhiteBox,
                    boxTextColor = AppColor.TextColor,
                    pictureModifier = Modifier
                        .width(80.dp)
                        .height(80.dp)

                )

                MediatingHomeCard(
                    modifier = Modifier
                        .weight(1f)
                        .height(200.dp),
                    topic = homeTopics[1],
                    boxColor = AppColor.TextColor,
                    boxTextColor = AppColor.WhiteBox,
                    pictureModifier = Modifier
                        .width(100.dp)
                        .height(160.dp)



                )
            }

            PlayMeditationCard(
                title = "Daily Thoughts",
                subTitle = "MEDIATION • 3-10 MIN",
                backgroundColor = AppColor.DarkPurple,
                backgroundImg = R.drawable.though_bg,
                onPlayClick = {

                }
            )


            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Recommended for you",
                style = AppTextStyle.semiBold20,
                color = AppColor.TextColor,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(vertical = 12.dp)
                )


            RecommendedCardList()



        }

    }
}

@Composable
fun RecommendedCardList(){
    val scrollState = rememberScrollState()

    Row(modifier = Modifier.horizontalScroll(scrollState)){
        for(i in recommendedTopics)
            RecommendedCard(i, onClick = {
                print(i.title)
            })
    }
}

@Composable

fun RecommendedCard(topic: Topic,     onClick: () -> Unit){
    Column (
        modifier = Modifier
            .width(170.dp)
         
            .padding(end = 8.dp)
            .clickable(onClick = onClick)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(color = topic.color, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = topic.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }


        Text(
            text = topic.title,
            style = AppTextStyle.semiBold16,
            color = AppColor.TextColor,
            modifier = Modifier
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Left
        )
        Text(
            text = "MEDITATION • 3-10 MIN",
            style = AppTextStyle.regular10,
            color = AppColor.TextHint,
            textAlign = TextAlign.Left
        )
    }
}

@Composable
fun DailyThoughtsCard(){
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 8.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppColor.DarkPurple)
                .paint(
                    painter = painterResource(id = R.drawable.though_bg),
                    contentScale = ContentScale.Crop
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Daily Thoughts",
                        color = Color.White,
                        style = AppTextStyle.bold18
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "MEDIATION • 3-10 MIN",
                        color = Color.White,
                        style = AppTextStyle.regular10
                    )
                }

                CircularPlayButton(
                    size = 40.dp,
                    backgroundColor = AppColor.White,
                    iconTint = AppColor.DarkPurple,
                    onClick = {

                    }
                )



            }


        }
    }
}


@Composable
fun MediatingHomeCard(
    modifier: Modifier,
    topic: Topic,
    boxColor: Color,
    boxTextColor: Color,
    pictureModifier: Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = topic.color
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = topic.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = pictureModifier
                    .align(Alignment.TopEnd)
            )


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {

                Spacer(
                    modifier = Modifier
                        .height(80.dp)
                )

                Text(
                    topic.title,
                    style = AppTextStyle.semiBold20,
                    color = topic.textColor,
                )
                Text(
                    topic.desc ?: "",
                    style = AppTextStyle.light12,
                    color = topic.textColor,
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "3-10 MIN",
                        style = AppTextStyle.regular12,
                        color = boxColor,
                    )

                    Card(
                        modifier = Modifier.padding(vertical = 5.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = boxColor
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "START",
                                style = AppTextStyle.semiBold12,
                                color = boxTextColor,
                            )
                        }
                    }
                }
            }
        }
    }
}
