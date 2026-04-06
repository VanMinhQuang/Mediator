package quang.app.mediator.core.component

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import quang.app.mediator.R


@Composable
fun AppImage(image: String,placeholder: Int? = null, modifier: Modifier? = null) {
    AsyncImage(
        model = image,
        contentDescription = "Test",
        error = painterResource(id = placeholder ?: R.drawable.meditate),
        contentScale = ContentScale.FillBounds,
        placeholder = painterResource(id = R.drawable.meditate),
        onError = {
                error ->
            Log.e("CoilImage", "Failed to load: ${error.result.throwable}")
        },
        modifier = modifier ?: Modifier
            .fillMaxWidth()
            .height(290.dp)
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
    )
}
