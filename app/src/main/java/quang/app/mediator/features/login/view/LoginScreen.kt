package quang.app.mediator.features.login.view

import AppButton
import TextFormFieldComponent
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.yourapp.ui.theme.AppTextStyle
import com.yourapp.ui.theme.AppTextStyle.withColor
import quang.app.mediator.R
import quang.app.mediator.core.component.CircularBackButton
import quang.app.mediator.core.component.LoadingDialog
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.features.Routes
import quang.app.mediator.features.login.viewModels.LoginEvent
import quang.app.mediator.features.login.viewModels.LoginState
import quang.app.mediator.features.login.viewModels.LoginUIEvent
import quang.app.mediator.features.login.viewModels.LoginViewModel


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val notificationPermissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)
    } else {
        null
    }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {

                is LoginUIEvent.ShowError ->
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()

                is LoginUIEvent.ShowSuccess -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    if (notificationPermissionState != null && !notificationPermissionState.status.isGranted) {
                        notificationPermissionState.launchPermissionRequest()

                    }


                    navController.navigate(Routes.WELCOME)
                }


            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColor.White)
            .padding(WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom).asPaddingValues())
    ) {

        LoginView(
            state = state,
            onEvent =  { viewModel.onEvent(it)},
            navController = navController
        )

        if (state.isLoading) {
            LoadingDialog()
        }


    }
}



@Composable
fun LoginView(state: LoginState, onEvent: (LoginEvent) -> Unit , navController: NavController) {


    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_login),
            contentDescription = "Background",

            contentScale = ContentScale.Crop,

            )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            ) {
                CircularBackButton(
                    onBack = { navController.popBackStack() },
                    borderColor = AppColor.Gray
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Welcome Back!",
                style = AppTextStyle.semiBold18.copy(fontSize = 28.sp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Facebook button
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                onTap = {

                },
                textStyle = AppTextStyle.semiBold14,
                color = AppColor.PrimaryBlue,
                content = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.facebook_icon),
                            contentDescription = "SVG Image",
                            modifier = Modifier
                                .size(18.dp)
                                .align(Alignment.CenterStart)
                        )
                        Text(
                            "CONTINUE WITH FACEBOOK",
                            style = AppTextStyle.semiBold14.copy(color = Color.White)
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Google button
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                onTap = {

                },
                textStyle = AppTextStyle.semiBold14,
                content = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = "SVG Image",
                            modifier = Modifier
                                .size(18.dp)
                                .align(Alignment.CenterStart)
                        )
                        Text(
                            "CONTINUE WITH GOOGLE",
                            style = AppTextStyle.regular14.copy(color = AppColor.TextColor)
                        )
                    }
                },
                color = Color.White,
                textColor = Color.Black,
                borderColor = Color.Gray,
                borderWidth = 0.2f
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "OR LOG IN WITH EMAIL",
                style = withColor(AppTextStyle.semiBold14, color = AppColor.TextHint)
            )

            Spacer(modifier = Modifier.height(16.dp))


            // Email input field
            TextFormFieldComponent(
                text = state.email,
                placeholder = "Email",
                onTextChange = { onEvent(LoginEvent.EnterEmail(it)) },

                )
            Spacer(modifier = Modifier.height(16.dp))

            TextFormFieldComponent(
                text = state.password,
                placeholder = "Password",
                onTextChange = { onEvent(LoginEvent.EnterPassword(it)) },
                isPassword = true,
            )

            Spacer(modifier = Modifier.height(16.dp))





            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                onTap = {
                    onEvent(LoginEvent.SubmitLogin)
                },
                textStyle = AppTextStyle.semiBold14,
                text = "LOG IN",
                gradient = AppColor.PrimaryGradient
            )

            Text(
                text = "Forgot your password?",
                style = withColor(
                    AppTextStyle.medium16.copy(fontSize = 14.sp),
                    color = AppColor.TextColor
                ),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable(
                        onClick = {

                        }
                    )
            )


            Spacer(modifier = Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "DON'T HAVE AN ACCOUNT YET? ",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "SIGN UP",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8E97FD),
                    modifier = Modifier.clickable { navController.navigate(Routes.REGISTER) }
                )
            }
        }
    }

}
