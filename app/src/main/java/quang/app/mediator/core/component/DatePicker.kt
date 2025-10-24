package quang.app.mediator.core.component

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.core.styles.AppColor
import java.time.LocalTime

@Composable
fun TimePicker(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
    initialTime: LocalTime = LocalTime.now(),
    onTimeSelected: (hour: Int, minute: Int) -> Unit,
    rowCount: Int = 3,
    ) {
    val rowHeight = 56.dp
    val totalHeight = rowHeight * rowCount

    Surface(
        color = AppColor.LightGray,
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 4.dp,
        modifier = modifier
    ) {
        WheelTimePicker(
            startTime = initialTime,
            rowCount = rowCount,
            textStyle = AppTextStyle.semiBold20,
            textColor = AppColor.TextColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(totalHeight),
            selectorProperties = WheelPickerDefaults.selectorProperties(
                enabled = true,
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFE1E1E5).copy(alpha = 0.15f),
                border = BorderStroke(2.dp, Color(0xFFE1E1E5)),

            )
        ) { snappedTime ->
            onTimeSelected(snappedTime.hour, snappedTime.minute)
        }
    }
}
