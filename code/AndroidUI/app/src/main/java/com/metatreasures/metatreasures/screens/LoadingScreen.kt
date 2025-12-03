package com.metatreasures.metatreasures.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.metatreasures.metatreasures.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.metatreasures.metatreasures.navigation.Navigation
import kotlinx.coroutines.delay

import coil.compose.rememberAsyncImagePainter
import coil.compose.AsyncImagePainter

@Composable
fun LoadingScreen(navController: NavController) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("android.resource://${LocalContext.current.packageName}/${R.drawable.loading}")
            .decoderFactory(coil.decode.GifDecoder.Factory())
            .build()
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
            is AsyncImagePainter.State.Error -> {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.error)
            }
            else -> {
                Image(
                    painter = painter,
                    contentDescription = stringResource(R.string.loading_animation_description),
                    modifier = Modifier.size(110.dp)
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Navigation.LogInScreen.route) {
            popUpTo(Navigation.LoadingScreen.route) { inclusive = true }
        }
    }
}
