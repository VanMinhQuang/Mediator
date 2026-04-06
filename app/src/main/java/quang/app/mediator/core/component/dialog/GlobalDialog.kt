package quang.app.mediator.core.component.dialog

import AppButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.core.styles.AppColor

@Composable
fun GlobalDialog() {
    val dialog by DialogManager.state
    if (!dialog.show) return

    Dialog(onDismissRequest = { DialogManager.dismiss() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, RoundedCornerShape(20.dp))
        ) {
            Column(
                modifier = Modifier.padding(20.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = dialogIcon(dialog.type),
                    contentDescription = null,
                    tint = dialogColor(dialog.type),
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = dialog.title,
                    style = AppTextStyle.semiBold18,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = dialog.message,
                    style = AppTextStyle.regular16.copy(textAlign = TextAlign.Center),

                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    dialog.dismissText?.let {
                        AppButton(
                            modifier = Modifier
                                .fillMaxWidth(),
                            onTap = {
                                dialog.onDismiss?.invoke()
                                DialogManager.dismiss()
                            },
                            textStyle = AppTextStyle.semiBold14,
                            text = dialog.confirmText,
                            color = AppColor.White,
                            textColor = AppColor.Primary,
                            borderColor = AppColor.Primary
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))

                    dialog.confirmText?.let{
                        AppButton(
                            modifier = Modifier
                                .fillMaxWidth(),
                            onTap = {
                                dialog.onConfirm?.invoke()

                                DialogManager.dismiss()
                            },
                            textStyle = AppTextStyle.semiBold14,
                            text = dialog.confirmText,
                            textColor = AppColor.White,
                            gradient = AppColor.PrimaryGradient

                        )

                    }
                }
            }
        }
    }
}
