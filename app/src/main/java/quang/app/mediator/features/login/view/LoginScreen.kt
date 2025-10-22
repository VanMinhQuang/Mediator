package quang.app.mediator.features.login.view

import AppButton
import TextFormFieldComponent
import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yourapp.ui.theme.AppTextStyle
import com.yourapp.ui.theme.AppTextStyle.withColor
import quang.app.mediator.R
import quang.app.mediator.core.component.CircularBackButton
import quang.app.mediator.core.component.LoadingDialog
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.features.Routes
import quang.app.mediator.features.login.viewModels.LoginEvent
import quang.app.mediator.features.login.viewModels.LoginUIEvent
import quang.app.mediator.features.login.viewModels.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var showLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is LoginUIEvent.ShowLoading -> showLoading = true
                is LoginUIEvent.HideLoading -> showLoading = false
                is LoginUIEvent.ShowError ->
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()

                is LoginUIEvent.ShowSuccess -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
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

        LoginView(viewModel, navController)

        if (showLoading) {
            LoadingDialog()
        }


    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController(),
        viewModel = LoginViewModel()
    )
}


@Composable
fun LoginView(viewModel: LoginViewModel, navController: NavController) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

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
                onTextChange = { viewModel.onEvent(LoginEvent.EnterEmail(it)) },

                )
            Spacer(modifier = Modifier.height(16.dp))

            TextFormFieldComponent(
                text = state.password,
                placeholder = "Password",
                onTextChange = { viewModel.onEvent(LoginEvent.EnterPassword(it)) },
                isPassword = true,
            )

            Spacer(modifier = Modifier.height(16.dp))





            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                onTap = {
                    viewModel.onEvent(LoginEvent.SubmitLogin)
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
