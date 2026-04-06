package quang.app.mediator.core.component.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class DialogType {
    SUCCESS, ERROR, WARNING, INFO
}


fun dialogColor(type: DialogType): Color = when (type) {
    DialogType.SUCCESS -> Color(0xFF4CAF50)
    DialogType.ERROR -> Color(0xFFF44336)
    DialogType.WARNING -> Color(0xFFFF9800)
    DialogType.INFO -> Color(0xFF2196F3)
}

fun dialogIcon(type: DialogType): ImageVector {
    return when (type) {
        DialogType.SUCCESS -> Icons.Default.CheckCircle
        DialogType.ERROR -> Icons.Default.Error
        DialogType.WARNING -> Icons.Default.Warning
        DialogType.INFO -> Icons.Default.Info
    }
}
