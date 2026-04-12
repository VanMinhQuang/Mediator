package quang.app.mediator.core.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.svg.SvgDecoder
import quang.app.mediator.R

@Composable
fun AppImage(
    url: String?,
    placeholder: Int = R.drawable.meditate,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
    ) {
    val context = LocalContext.current

    val request = remember(url) {
        ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .decoderFactory(SvgDecoder.Factory())
            .build()
    }

    AsyncImage(
        model = request,
        contentDescription = "Network Image",
        placeholder = painterResource(id = placeholder),
        error = painterResource(id = placeholder),
        contentScale = contentScale,
        modifier = modifier,
        onError = { error ->
            Log.e("AppNetworkImage", "Failed: ${error.result.throwable}")
        }
    )
}
