package quang.app.mediator.features.register.view

import AppButton
import TextFormFieldComponent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import quang.app.mediator.features.register.viewModels.RegisterEvent
import quang.app.mediator.features.register.viewModels.RegisterUiEvent
import quang.app.mediator.features.register.viewModels.RegisterViewModel


@Composable
fun RegisterScreen(navController: NavController,

                   viewModel: RegisterViewModel = hiltViewModel()
) {

    var showLoading by  remember { mutableStateOf(false) }
    val context = LocalContext.current


    LaunchedEffect(Unit)  {
        viewModel.event.collect { event ->
            when(event) {
                is RegisterUiEvent.ShowLoading -> showLoading = true
                is RegisterUiEvent.HideLoading -> showLoading = false
                is RegisterUiEvent.ShowError ->
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                is RegisterUiEvent.ShowSuccess ->
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                is RegisterUiEvent.NavigateToHome -> navController.popBackStack()

            }
        }
    }


    Box(
        modifier = Modifier.fillMaxSize().background(color = AppColor.White)
    ){

        RegisterView(viewModel,navController)

        if(showLoading){
            LoadingDialog()
        }


    }
}

@Composable
fun RegisterView(viewModel: RegisterViewModel, navController: NavController){
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            ){
                CircularBackButton(onBack = { navController.popBackStack() }, borderColor = AppColor.Gray)
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Create your account",
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
                        Text("CONTINUE WITH FACEBOOK", style = AppTextStyle.semiBold14.copy(color = Color.White))
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
                        Text("CONTINUE WITH GOOGLE", style = AppTextStyle.regular14.copy(color = AppColor.TextColor))
                    }
                },
                color = Color.White,
                textColor = Color.Black,
                borderColor = Color.Gray,
                borderWidth = 0.2f
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("OR LOG IN WITH EMAIL", style = withColor(AppTextStyle.semiBold14, color = AppColor.TextHint))

            Spacer(modifier = Modifier.height(16.dp))

            TextFormFieldComponent(
                text = state.userName,
                placeholder = "User Name",
                onTextChange = { viewModel.onEvent(RegisterEvent.NameChanged(it)) },
                validator = state.userName.isNotEmpty()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Email input field
            TextFormFieldComponent(
                text = state.email,
                placeholder = "Email",
                onTextChange = { viewModel.onEvent(RegisterEvent.EmailChanged(it)) },
                validator = state.email.isNotEmpty()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextFormFieldComponent(
                text = state.password,
                placeholder = "Password",
                onTextChange = { viewModel.onEvent(RegisterEvent.PasswordChanged(it)) },
                isPassword = true,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("I have read the ", style = AppTextStyle.regular14)
                    Text(
                        text = "Privacy Policy",
                        style = AppTextStyle.semiBold14.copy(color = AppColor.PrimaryBlue)
                    )
                }

                Checkbox(
                    checked = state.isReadPolicy,
                    onCheckedChange ={
                        viewModel.onEvent(RegisterEvent.TogglePrivacyPolicy)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppColor.PrimaryBlue,
                        checkmarkColor = AppColor.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                onTap = {

                },
                textStyle = AppTextStyle.semiBold14,
                text = "GET STARTED",
                gradient = AppColor.PrimaryGradient
            )
        }
    }
}
@Preview
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    RegisterScreen(navController)
}

