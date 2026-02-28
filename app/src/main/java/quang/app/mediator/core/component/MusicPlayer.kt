package quang.app.mediator.core.component

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.core.styles.AppColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayer(

    progress: Float,
    onProgressChange: (Float) -> Unit,
    currentTime: String,
    totalTime: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {


        Spacer(modifier = Modifier.height(18.dp))


       Slider(
            value = progress,
            onValueChange = onProgressChange,
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                thumbColor = AppColor.TextColor,
                activeTrackColor = AppColor.TextColor,
                inactiveTrackColor = AppColor.Gray700
            ),
           track = { sliderState ->
               Box(
                   modifier = Modifier
                       .height(6.dp)
                       .fillMaxWidth()
               ) {
                   // Inactive track
                   Box(
                       modifier = Modifier
                           .fillMaxSize()
                           .background(AppColor.Gray700, CircleShape)
                   )

                   // Active track
                   Box(
                       modifier = Modifier
                           .fillMaxWidth(sliderState.value)
                           .height(6.dp)
                           .background(AppColor.TextColor, CircleShape)
                   )
               }
           },

           // --- THUMB ---
           thumb = {
               Box(
                   modifier = Modifier
                       .size(14.dp)
                       .background(AppColor.TextColor, CircleShape)
               )
           },
        )

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(currentTime, style = AppTextStyle.medium14)
            Text(totalTime, style = AppTextStyle.medium14)
        }

        Spacer(modifier = Modifier.height(32.dp))


    }
}
