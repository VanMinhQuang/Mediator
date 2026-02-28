package quang.app.mediator.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import quang.app.mediator.core.styles.AppColor

@Composable
fun CircularBackButton(
    onBack: () -> Unit,
    backgroundColor: Color = Color.White,
    iconColor: Color = Color.Black,
    borderColor: Color = AppColor.Gray700,
    isForward: Boolean = false
) {
    IconButton(
        onClick = onBack,
        modifier = Modifier
            .size(48.dp)
            .border(width = 1.dp, color = borderColor, shape = CircleShape) // Add the border here
            .clip(CircleShape)
            .background(backgroundColor)
    ) {
        Icon(
            imageVector = if (isForward) Icons.AutoMirrored.Filled.ArrowForward else Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Go Back",
            tint = iconColor
        )
    }
}
