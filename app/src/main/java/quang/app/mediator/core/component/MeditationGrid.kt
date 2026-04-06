package quang.app.mediator.core.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.R
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.domain.model.MeditationModel


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

            val column = index % 2


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
