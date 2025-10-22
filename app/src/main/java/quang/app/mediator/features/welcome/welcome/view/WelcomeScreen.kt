package quang.app.mediator.features.welcome.welcome.view

import AppButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yourapp.ui.theme.AppTextStyle
import quang.app.mediator.R
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.features.Routes

@Composable
fun WelcomeScreen(navController: NavController) {
    Scaffold (
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
    ) {
        paddingValues ->
            WelcomeView(navController,Modifier
                .fillMaxSize()
                .background(AppColor.Primary).padding(paddingValues))
    }

}

@Composable
fun WelcomeView(navController: NavController, modifier: Modifier) {
    Box(
        modifier = modifier
    ) {

        Image(
            painter = painterResource(id = R.drawable.welcome_bg),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.app_logo_white),
                contentDescription = "Logo",
                tint = Color.Unspecified,
                modifier = Modifier
                    .height(180.dp)
                    .width(180.dp)
            )



            Text(
                "Hi Quang, Welcome",
                style = AppTextStyle.semiBold28.copy(color = AppColor.TextCream),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text("To Silent Moon", style = AppTextStyle.light28.copy(color = AppColor.TextCream))

            Text(
                "Explore the app, Find some peace of mind to prepare for meditation.",
                style = AppTextStyle.light18.copy(
                    color = AppColor.TextCream,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .padding(horizontal = 15.dp)
            )

            Spacer(modifier = Modifier.weight(1f))


            AppButton(
                text = "GET STARTED",
                color = AppColor.White,
                textStyle = AppTextStyle.medium14,
                textColor = AppColor.TextColor,
                modifier = Modifier
                    .padding(vertical = 60.dp)
                    .height(60.dp)
                    .fillMaxSize(),
                onTap = {
                    navController.navigate(Routes.TOPIC)
                }
            )


        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    WelcomeScreen(navController)
}
