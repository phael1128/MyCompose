package com.example.component.presentation.ui.basic.component1.chap10

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ComposeNetworkImage() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val imageResourceUrl = "https://www.nintendo.com/kr/character/kirby/assets/img/intro/kirby-star2.png"

        GlideImage(
            model = imageResourceUrl,
            contentDescription = "커비1",
        ) {
            it.listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("phael", "Image Load Fail")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("phael", "Image Load Success")
                    return true
                }

            })
        }
    }
}

@Preview
@Composable
fun ComposeNetworkImagePreview() {
    ComposeNetworkImage()
}