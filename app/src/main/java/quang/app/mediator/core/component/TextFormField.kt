

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.yourapp.ui.theme.AppTextStyle
import com.yourapp.ui.theme.AppTextStyle.withColor
import quang.app.mediator.core.styles.AppColor


@Composable
fun TextFormFieldComponent(
    placeholder: String,
    text: String,
    onTextChange: (String) -> Unit,
    isPassword: Boolean = false,
    color: Color = AppColor.TextBackGround,
    style: TextStyle = AppTextStyle.regular14,
    leading: @Composable (() -> Unit)? = null,
    validator: Boolean = false
) {
    var isObscured by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .background(color, shape = RoundedCornerShape(10.dp))
            .height(50.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier

                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Leading icon
            leading?.invoke()

            // Text field
            TextField(
                value = text,
                onValueChange = onTextChange,
                placeholder = { Text(placeholder, style = withColor(AppTextStyle.regular14,color = AppColor.TextHint)) },
                textStyle = style,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent),
                singleLine = true,
                visualTransformation = if (isPassword && isObscured)
                    PasswordVisualTransformation()
                else
                    VisualTransformation.None,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    if (isPassword) {
                        IconButton(onClick = { isObscured = !isObscured }) {
                            Icon(
                                imageVector = if (isObscured)
                                    Icons.Filled.VisibilityOff
                                else
                                    Icons.Filled.Visibility,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        }
                    }
                }
            )

            if (validator) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Valid",
                    tint = Color.Green
                )
            }
        }
    }
}
