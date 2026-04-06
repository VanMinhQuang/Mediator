package quang.app.mediator.core.component.dialog

import androidx.compose.runtime.mutableStateOf

object DialogManager {
    private val _state = mutableStateOf(DialogState())
    val state = _state

    fun show(
        type: DialogType = DialogType.INFO,
        title: String,
        message: String,
        confirmText: String = "OK",
        dismissText: String? = null,
        onConfirm: (() -> Unit)? = null,
        onDismiss: (() -> Unit)? = null
    ) {
        _state.value = DialogState(
            show = true,
            type = type,
            title = title,
            message = message,
            confirmText = confirmText,
            dismissText = dismissText,
            onConfirm = onConfirm,
            onDismiss = onDismiss
        )
    }


    fun showSuccess(message: String,onConfirm: (() -> Unit)? = null) {
        _state.value = DialogState(
            show = true,
            type = DialogType.SUCCESS,
            title = "Success",
            message = message,
            confirmText = "OK",
            onConfirm = onConfirm
        )
    }

    fun showError(message: String,onConfirm: (() -> Unit)? = null) {
        _state.value = DialogState(
            show = true,
            type = DialogType.ERROR,
            message = message,
            confirmText = "OK",
            onConfirm = onConfirm
        )
    }

    fun dismiss() {
        _state.value = _state.value.copy(show = false)
    }
}
