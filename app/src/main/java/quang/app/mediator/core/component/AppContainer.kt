package quang.app.mediator.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import quang.app.mediator.core.styles.AppColor


@Composable
fun AppContainer(
    isLoading: Boolean = false,
    appColor: Color = AppColor.White,
    content: @Composable () -> Unit
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = appColor)
    ) {
        content()

        if(isLoading){
            LoadingDialog()
        }
    }

}
