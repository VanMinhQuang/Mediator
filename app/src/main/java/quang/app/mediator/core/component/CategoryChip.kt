package quang.app.mediator.core.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.features.main.meditate.view.MeditateMenuItem

@Composable
fun CategoryChip(
    menuMediates: List<MeditateMenuItem>,
    selectedType: MeditateMenuItem?,
    onClick: (MeditateMenuItem) -> Unit = {},
    selectedColor: Color? = null,
    unSelectedColor: Color? = null,
    unSelectedBackground: Color? = null,
){



    LazyRow(
        modifier = Modifier.fillMaxWidth(),

        ) {
        items(menuMediates) {
                item ->
            val isSelected = item == selectedType
            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected)  AppColor.Primary
                else unSelectedBackground ?: AppColor.Gray700,
                animationSpec = tween(300)
            )

            val textColor by animateColorAsState(
                targetValue = (if (item.isSelected) selectedColor ?: AppColor.TextColor
                else unSelectedColor ?: AppColor.Gray700),
                animationSpec = tween(300)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)

            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(backgroundColor)
                        .clickable { onClick(item) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = AppColor.White,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.title,
                    color = textColor,
                    style = AppTextStyle.regular14
                )
            }
        }
    }



}
