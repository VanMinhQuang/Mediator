package quang.app.mediator.features.course.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.core.component.CircularPlayButton
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.domain.model.Music

@Composable
fun NarratorContentList(musics: List<Music>) {
    val musicSelected = rememberSaveable { mutableStateOf<Music?>(null) }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(musics) { music ->
            val isSelected = musicSelected.value == music
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(AppColor.Gray.copy(alpha = 0.1f))
                    .padding(16.dp)
                    .clickable {
                        musicSelected.value = music;
                    },
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularPlayButton(
                    onClick = { /* Handle play action */ },
                    size = 48.dp,
                    backgroundColor = if (isSelected)  AppColor.Primary else Color.White,
                    borderColor =  if (isSelected) Color.Transparent else AppColor.TextHint,
                    borderWidth = if (isSelected) 0.dp else 1.dp,
                    iconTint = if (isSelected) Color.White else AppColor.TextHint
                )
                Column (
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = music.title,
                        style = AppTextStyle.semiBold18,
                        color = AppColor.TextColor
                    )
                    Text(
                        text = "${music.length / 60} MIN",
                        style = AppTextStyle.regular14,
                        color = AppColor.TextHint
                    )
                }


            }
        }
    }
}
