package quang.app.mediator.features.course.view

import android.util.Log
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.sharp.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import quang.app.mediator.R
import quang.app.mediator.core.component.CircularBackButton
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.domain.model.MeditationModel


@Composable
fun CourseScreen(navController: NavController){
    val topic = MeditationModel.dummyList()[0]
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom)

    ) {
        innerPadding -> Box(
            modifier = Modifier.padding(innerPadding)
        ){
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Box{
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
                            .height(320.dp)
                            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp).padding(horizontal = 16.dp),
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
            }
        }
    }
}

@Preview
@Composable
fun CourseScreenPreview() {
    CourseScreen(navController = rememberNavController())
}
