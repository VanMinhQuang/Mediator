package quang.app.mediator.core.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AppButton(modifier: Modifier = Modifier,onTap: () -> Unit,color: Color = Color(0xFF8E97FD), text: String, textColor: Color = Color.White) {
    Button(
        onClick = { onTap() },
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        modifier = modifier,
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}