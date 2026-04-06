package quang.app.mediator.features.main.sleep.view

import AppButton
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yourapp.ui.theme.AppTextStyle
import kotlinx.coroutines.launch
import quang.app.mediator.R
import quang.app.mediator.core.component.CategoryChip
import quang.app.mediator.core.constants.AppConstants.menuMediates
import quang.app.mediator.core.styles.AppColor


@Composable
fun SleepMainView() {
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    val animatedContainerColor by animateColorAsState(
        targetValue = if (startAnimation) AppColor.DarkBackground else Color.White,
        animationSpec = tween(durationMillis = 500),        label = "sleepBackground"
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(animatedContainerColor)) {

        // Moon animation in the background
        AnimatedMoonSimplified()

        // Content on top
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text(
                text = "Sleep Stories",
                style = AppTextStyle.semiBold32.copy(color = AppColor.White),
                modifier = Modifier.padding(top = 60.dp)
            )
            Text(
                text = "Smoothing bedtime stories to help you fall \n into a deep and natural sleep",
                style = AppTextStyle.light18.copy(color = AppColor.White,   lineHeight = 25.sp ),
                modifier = Modifier.padding( bottom = 10.dp),
                textAlign = TextAlign.Center,

            )


            CategoryChip(
                menuMediates = menuMediates,
                selectedType = null,
                selectedColor = AppColor.Gray700,
                unSelectedColor = AppColor.Gray900,
                unSelectedBackground = AppColor.Gray100
//                onClick = {
//                        menuItem ->
//                    viewModel.onEvent(MeditateEvent.SelectMeditationType(menuItem))
//                }
            )

            Box{
                Image(
                    painter = painterResource(id = R.drawable.sunny),
                    contentDescription = "Sunny",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .height(250.dp)
                )

                Column (
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Sunny Day",
                        style = AppTextStyle.semiBold32.copy(color = AppColor.White),
                        modifier = Modifier

                    )
                    Text(
                        text = "Non-stop 8 hour mixes of our most popular sleep audio",
                        style = AppTextStyle.light20.copy(color = AppColor.White),
                        modifier = Modifier
                            .padding(12.dp),
                        textAlign = TextAlign.Center
                    )
                    Box(modifier = Modifier.padding(vertical = 12.dp)) {
                        AppButton(
                            text = "START",
                            color = AppColor.White,
                            textStyle = AppTextStyle.semiBold14.copy(fontSize = 14.sp),
                            textColor = AppColor.TextColor,


                            onTap = {
                            }
                        )
                    }


                }

            }



        }
    }
}


@Composable
fun AnimatedMoonSimplified() {
    val moonOffset = remember { Animatable(200f) }
    val moonAlpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        launch { moonOffset.animateTo(0f, tween(1200, easing = FastOutSlowInEasing)) }
        launch { moonAlpha.animateTo(1f, tween(1200)) }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background clouds
        Image(
            painter = painterResource(id = R.drawable.dark_cloud),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth()
        )

        // Moon
        Image(
            painter = painterResource(id = R.drawable.moon_start),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .offset(y = moonOffset.value.dp)
                .alpha(moonAlpha.value)
        )


    }
}



@Preview
@Composable
fun SleepMainContentPreview() {
    SleepMainView()
}
