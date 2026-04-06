
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.core.styles.AppColor

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onTap: () -> Unit,
    text: String? = null,
    color: Color = AppColor.Primary,
    gradient: Brush? = null,
    textColor: Color = Color.White,
    borderColor: Color = Color.Transparent,
    borderWidth: Float = 1f,
    textStyle: TextStyle = AppTextStyle.bold14,
    content: (@Composable () -> Unit)? = null,
    isEnable: Boolean = true
) {
    val shape = RoundedCornerShape(50)

    Button(
        enabled = isEnable,
        onClick = onTap,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, // Make container transparent
            contentColor = textColor
        ),
        shape = shape,
        modifier = modifier
            .then(
                if(!isEnable) {
                    Modifier.background(color = AppColor.Gray, shape = shape)
                } else
                if (gradient != null) {
                    Modifier.background(brush = gradient, shape = shape)
                } else {
                    Modifier.background(color = color, shape = shape)
                }
            )
            .border(width = borderWidth.dp, color = borderColor, shape = shape),
        contentPadding = ButtonDefaults.ContentPadding
    ) {
        if (text != null) {
            Text(text = text, style = textStyle.copy(color = textColor))
        } else {
            content?.invoke()
        }
    }
}
