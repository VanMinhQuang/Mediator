package quang.app.mediator.features.welcome.topic.view

import AppButton
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.R
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.data.model.Topic
import quang.app.mediator.features.welcome.topic.viewModel.TopicEvent
import quang.app.mediator.features.welcome.topic.viewModel.TopicViewModel


@Composable
fun TopicScreen(navController: NavController, viewModel: TopicViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.White)
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.topic_bg),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(AppColor.Cream)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(70.dp))

            Text("What Brings you", style = AppTextStyle.semiBold28)
            Text("to Silent Moon?", style = AppTextStyle.light28)

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                "choose a topic to focus on:",
                style = AppTextStyle.light20.copy(color = AppColor.TextHint)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Make grid take up remaining space
            Box(modifier = Modifier.weight(1f)) {
                TopicGrid(
                    items = state.topics,
                    onClick = { topic ->
                        viewModel.onEvent(TopicEvent.TopicSelected(topic.topicId))
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(
                    if (state.selectedTopicId != null) 16.dp else 70.dp
                )
            )
        }

        // Sticky bottom button (overlays content)
        if (state.selectedTopicId != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp, vertical = 24.dp)
                    .shadow(20.dp, RoundedCornerShape(30.dp))
            ) {
                AppButton(
                    text = "Next",
                    textStyle = AppTextStyle.semiBold18,
                    textColor = AppColor.White,
                    gradient = AppColor.PrimaryGradient,
                    onTap = {
                        // Navigation or logic here
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopicGrid(items: List<Topic>, onClick: (Topic) -> Unit) {
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

            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .clickable { onClick(topic) }
                    .fillMaxWidth()
                    .wrapContentHeight(),
                elevation = CardDefaults.cardElevation(6.dp),
                colors = CardDefaults.cardColors(containerColor = topic.color)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(topic.color)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = topic.image),
                            contentDescription = topic.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(16.dp))
                        )

                        Text(
                            text = topic.title,
                            style = AppTextStyle.semiBold18.copy(
                                color = topic.textColor,
                                textAlign = TextAlign.Left
                            ),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    if (topic.isPicked) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Selected",
                            tint = AppColor.White,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(5.dp)
                                .size(28.dp)
                                .background(
                                    color = AppColor.TextColor.copy(alpha = 0.3f),
                                    shape = CircleShape
                                )
                                .padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}



@Preview
@Composable
fun TopicScreenPreview() {
    val navController = rememberNavController()
    TopicScreen(navController)
}
