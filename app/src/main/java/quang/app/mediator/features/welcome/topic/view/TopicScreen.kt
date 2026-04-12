package quang.app.mediator.features.welcome.topic.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import quang.app.mediator.core.component.AppImage
import quang.app.mediator.core.component.CircularBackButton
import quang.app.mediator.core.component.dialog.DialogManager
import quang.app.mediator.core.navigation.Routes
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.domain.model.MediationTopic
import quang.app.mediator.features.welcome.topic.viewModel.TopicEvent
import quang.app.mediator.features.welcome.topic.viewModel.TopicUIEvent
import quang.app.mediator.features.welcome.topic.viewModel.TopicViewModel


@Composable
fun TopicScreen(navController: NavController, viewModel: TopicViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle().value


    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is TopicUIEvent.ShowError -> {
                    DialogManager.showError(event.message)
                }
            }
        }
    }
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


            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text("What Brings you", style = AppTextStyle.semiBold28)
                    Text("to Silent Moon?", style = AppTextStyle.light28)
                }

                Spacer(modifier = Modifier.weight(1f))

                if (state.selectedTopicIds != null)
                    CircularBackButton(
                        onBack = {
                            viewModel.saveSelectedTopic()
                            navController.navigate(Routes.REMINDER) },
                        backgroundColor = AppColor.White,
                        iconColor = AppColor.TextColor,
                        borderColor = AppColor.TextHint,
                        isForward = true
                    )
            }


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
                        viewModel.onEvent(TopicEvent.TopicSelected(topic.id))
                    }
                )
            }

        
        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopicGrid(items: List<MediationTopic>, onClick: (MediationTopic) -> Unit) {
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
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {


                    AppImage(
                        url = topic.imageUrl,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                    )



                    // TITLE (BOTTOM LEFT)
                    Text(
                        text = topic.title,
                        style = AppTextStyle.semiBold18.copy(
                            color = AppColor.White,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(12.dp)
                    )

                    // CHECK ICON (TOP RIGHT)
                    if (topic.isPicked) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Selected",
                            tint = AppColor.White,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
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
