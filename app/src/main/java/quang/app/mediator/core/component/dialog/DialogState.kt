package quang.app.mediator.core.component.dialog




data class DialogState(
    val show: Boolean = false,
    val type: DialogType = DialogType.INFO,
    val title: String = "",
    val message: String = "",
    val confirmText: String? = null,
    val dismissText: String? = null,
    val onConfirm: (() -> Unit)? = null,
    val onDismiss: (() -> Unit)? = null
)
