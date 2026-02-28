package quang.app.mediator.features.main.music.view

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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.R
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.domain.model.Music
import quang.app.mediator.features.Routes

@Composable
fun MusicView(navController: NavController) {

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
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text("Sleep Music", style = AppTextStyle.bold24.copy(fontSize = 26.sp), color = AppColor.TextColor)
            Spacer(modifier = Modifier.height(10.dp))

            MusicGrid(
                Music.dummyList(),
                onClick = {
                    navController.navigate(Routes.PLAY_MUSIC)
                }
            )
        }
    }
}

@Preview
@Composable
fun MusicViewPreview() {
    val navController = rememberNavController()
    MusicView(navController)
}


@Composable
fun MusicGrid(musics: List<Music>, onClick: (Music) -> Unit) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
        ,
        horizontalArrangement = Arrangement.spacedBy(8.dp),

    ) {
        items(musics, key = { it.id }) { meditationItem ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onClick(meditationItem) })

            ) {
                Column {

                    AsyncImage(
                        model = meditationItem.imageUrl,
                        contentDescription = meditationItem.title,
                        contentScale = ContentScale.Crop,
                        // Add a placeholder for a better user experience
                        placeholder = painterResource(id = R.drawable.meditate),
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .padding(vertical = 8.dp)
                            .clip(androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
                    )


                    Text(
                        text = meditationItem.title,
                        style = AppTextStyle.semiBold18.copy(
                            color = AppColor.TextColor,
                            textAlign = TextAlign.Left
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = meditationItem.subTitle,
                        style = AppTextStyle.regular16.copy(
                            color = AppColor.TextHint,
                            textAlign = TextAlign.Left
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

            }
        }
    }
}
