
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.ImageLoader
import coil3.compose.rememberAsyncImagePainter
import coil3.gif.GifDecoder


@Composable
fun GifPlayer(assetName: String, modifier: Modifier, scale: ContentScale) {
    val context = LocalContext.current

    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                // GifDecoder.Factory implements Decoder.Factory, so this works
                add(GifDecoder.Factory())
            }
            .build()
    }

    val painter = rememberAsyncImagePainter(
        model = "file:///android_asset/$assetName",
        imageLoader = imageLoader
    )

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = scale,
        modifier = modifier.fillMaxSize()
    )
}
