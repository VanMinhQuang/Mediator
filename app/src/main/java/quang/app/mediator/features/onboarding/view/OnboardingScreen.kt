package quang.app.mediator.features.onboarding.view

import AppButton
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import quang.app.mediator.R

import quang.app.mediator.core.styles.AppColor

@Composable
fun OnboardingScreen(navController: NavController) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        WaveBackground(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFF9F0E3).copy(alpha = 0.5f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Icon(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Logo",
                tint = Color.Unspecified,
                modifier = Modifier.size(180.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Illustration
            Image(
                painter = painterResource(id = R.drawable.listen_music),
                contentDescription = "Relax",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(100.dp))

            // Title
            Text(
                text = "We are what we do",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle
            Text(
                text = "Thousand of people are using Silent Moon\nfor smalls meditation",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Sign Up button
            AppButton(
                onTap = {
                    navController.navigate("register")

                    print("sign up tapped") },
                text = "SIGN UP",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                gradient = AppColor.PrimaryGradient,
                textColor = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Login
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "ALREADY HAVE AN ACCOUNT? ",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Box(
                    modifier = Modifier

                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = { offset ->
                                    navController.navigate("register")
                                },

                            )
                        }

                ) {
                    Text(
                        text = "LOG IN",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF8E97FD),
                        modifier = Modifier.clickable { /* TODO */ }
                    )
                }
            }
        }
    }
}

@Composable
fun WaveBackground(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFFF9F0E3)
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        val path = Path().apply {
            moveTo(0f, 0f) // start at top-left
            lineTo(0f, height * 0.58f)

            quadraticTo(
                width * 0.25f, height * 0.68f,
                width * 0.5f, height * 0.58f
            )
            quadraticTo(
                width * 0.75f, height * 0.48f,
                width, height * 0.58f
            )

            lineTo(width, 0f)   // back to top-right
            lineTo(0f, 0f)      // close to top-left
            close()
        }

        drawPath(
            path = path,
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    val navController = rememberNavController()
    OnboardingScreen(navController)
}



