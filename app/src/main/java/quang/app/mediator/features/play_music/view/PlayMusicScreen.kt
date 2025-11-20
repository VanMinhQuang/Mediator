package quang.app.mediator.features.play_music.view

import GifPlayer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import quang.app.mediator.core.component.CircularBackButton
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

    Box(modifier = modifier.fillMaxSize()){


        GifPlayer(
            "meditate_music_loop.gif",
            scale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(top = 12.dp)
            ){
                CircularBackButton(
                    onBack = { navController.popBackStack() },
                    borderColor = AppColor.Gray
                )
            }
        }
    }
}
