package quang.app.mediator.features.play_music.view

import GifPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.sharp.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.R
import quang.app.mediator.core.component.CircularBackButton
import quang.app.mediator.core.component.MusicPlayer
import quang.app.mediator.core.styles.AppColor


@Composable
fun PlayMusicScreen(navController: NavController) {
    Scaffold (
        contentWindowInsets = WindowInsets(0.dp, 8.dp, 0.dp, 0.dp)
    ) {
            paddingValues ->
        PlayMusicContent(modifier = Modifier.padding(paddingValues), navController = navController)
    }
}

@Preview
@Composable
fun PlayMusicScreenPreview() {
    val navController = rememberNavController()
    PlayMusicScreen(navController)
}

@Composable
fun PlayMusicContent(modifier: Modifier, navController: NavController) {
    var isPlaying by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0.3f) } // 0f → 1f
    val currentTime = "01:23"
    val totalTime = "03:00"

    Box(modifier = modifier.fillMaxSize()){


        GifPlayer(
            assetName = "meditate_music_loop.gif",
            isPlaying = isPlaying,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left side - Back button
                CircularBackButton(
                    onBack = { navController.popBackStack() },
                    borderColor = AppColor.Gray
                )

                // Right side - Favorite + Download
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(48.dp)
                            .border(width = 1.dp, shape = CircleShape, color = AppColor.Gray600)
                            .clip(CircleShape)
                            .background(color = AppColor.Gray600)

                    ) {
                        Icon(
                            imageVector =  Icons.Rounded.FavoriteBorder ,
                            contentDescription = "Favorite",
                            tint = AppColor.White
                        )
                    }

                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(48.dp)
                            .border(width = 1.dp, shape = CircleShape, color = AppColor.Gray600)
                            .clip(CircleShape)
                            .background(color = AppColor.Gray600)

                    ) {
                        Icon(
                            imageVector =  Icons.Sharp.Download ,
                            contentDescription = "Download",
                            tint = AppColor.White
                        )
                    }


                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    "Focus Attention",
                    style = AppTextStyle.bold24.copy(fontSize = 34.sp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "7 DAYS OF CALM",
                    style = AppTextStyle.medium14.copy(color = AppColor.Gray700)
                )

                Spacer(modifier = Modifier.height(48.dp))

                Row (
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(48.dp)


                    ) {
                        Icon(
                            painter =  painterResource(id = R.drawable.back_15),
                            contentDescription = "Back 15",
                            tint = AppColor.Gray700
                        )
                    }


                    IconButton(
                        onClick = {isPlaying = !isPlaying},
                        modifier = Modifier.size(140.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            // Background circle
                            Icon(
                                painter = painterResource(id = R.drawable.black_circle),
                                contentDescription = null,

                                modifier = Modifier.fillMaxSize()
                            )


                            Icon(
                                imageVector = Icons.Filled.PlayArrow,
                                contentDescription = "Play",
                                tint = AppColor.White,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }


                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(48.dp)


                    ) {
                        Icon(
                            painter =  painterResource(id = R.drawable.skip_15),
                            contentDescription = "Skip 15",
                            tint = AppColor.Gray700
                        )
                    }
                }


                MusicPlayer(

                    progress = progress,
                    onProgressChange = { progress = it },
                    currentTime = currentTime,
                    totalTime = totalTime
                )
            }

        }
    }
}
