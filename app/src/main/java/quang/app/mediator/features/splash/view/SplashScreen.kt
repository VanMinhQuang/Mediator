package quang.app.mediator.features.splash.view

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import quang.app.mediator.R
import quang.app.mediator.core.component.app.UserStatus
import quang.app.mediator.core.navigation.Routes
import quang.app.mediator.core.navigation.navigateReplace
import quang.app.mediator.features.splash.viewmodel.SplashViewModel


@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.userStatus) {
        when(state.userStatus){
            UserStatus.LOADING -> {
                //Do Nothing
            }
            UserStatus.UNAUTHENTICATED -> {
                navController.navigateReplace(Routes.ONBOARDING, Routes.SPLASH)
            }
            UserStatus.AUTHENTICATED -> {
                navController.navigateReplace(Routes.WELCOME, Routes.SPLASH)
            }

        }
    }



    val infiniteTransition = rememberInfiniteTransition(label = "gradient")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offset"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF9178FF), // ~0.57, 0.47, 1.0
                        Color(0xFF6CA9FF)  // ~0.43, 0.66, 1.0
                    ),
                    start = Offset(offset, 0f),
                    end = Offset(offset + 500f, 500f)
                )
            ),
        contentAlignment = Alignment.Center

    ) {
        Icon(
            painter = painterResource(id = R.drawable.app_logo_white),
            contentDescription = "Logo",
            tint = Color.Unspecified,
            modifier = Modifier
                .size(300.dp)
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()
    SplashScreen(navController)
}
