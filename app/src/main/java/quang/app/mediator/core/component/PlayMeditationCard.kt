package quang.app.mediator.core.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.core.styles.AppColor


@Composable
fun PlayMeditationCard(
    title: String,
    subTitle: String,
    backgroundColor: Color,
    textColor: Color = AppColor.White,
    backgroundImg: Int,
    onPlayClick: () -> Unit,
    isReverseColorButton: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 8.dp)
            .clickable(
                onClick = onPlayClick
            )

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color =backgroundColor)
                .paint(
                    painter = painterResource(id = backgroundImg),
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
                        text = title,
                        color = textColor,
                        style = AppTextStyle.bold18
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = subTitle,
                        color = textColor,
                        style = AppTextStyle.regular10
                    )
                }

                CircularPlayButton(
                    size = 40.dp,
                    backgroundColor = if(isReverseColorButton) AppColor.DarkPurple else AppColor.White,
                    iconTint = if(isReverseColorButton) AppColor.White else AppColor.DarkPurple,
                    onClick = {

                    }
                )



            }


        }
    }
}
