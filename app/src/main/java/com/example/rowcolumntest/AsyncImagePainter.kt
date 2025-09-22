package com.example.rowcolumntest

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun AsyncImagePainter(
    imageUrl: String,
    withCrossFade: Boolean,
    crossFadeDuration: Int = 100,
    modifier: Modifier,
    contentScale: ContentScale,
    alignment: Alignment = Alignment.TopCenter,
    placeholder: Painter,
    error: Painter,
    colorFilter: ColorFilter? = null,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).apply {
            data(imageUrl)
            if (withCrossFade) {
                crossfade(true)
                crossfade(crossFadeDuration)
            }
        }.build(),
        contentDescription = stringResource(R.string.app_name),
        modifier = modifier,
        contentScale = contentScale,
        alignment = alignment,
        colorFilter = colorFilter,
        placeholder = placeholder,
        error = error
    )
}