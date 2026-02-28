
import android.content.Context
import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView

@Composable
fun GifPlayer(

    assetName: String,
    modifier: Modifier = Modifier,
    isPlaying: Boolean
) {
    AndroidView(
        factory = { ctx ->
            GifImageView(ctx).apply {
                val inputStream = ctx.assets.open(assetName)
                setImageDrawable(GifDrawable(inputStream))
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
        },
        modifier = modifier,
        update = { view ->
            val drawable = view.drawable as? GifDrawable
            if (isPlaying) drawable?.start() else drawable?.stop()
        }
    )
}
