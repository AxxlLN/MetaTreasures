package com.metatreasures.metatreasures.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.metatreasures.metatreasures.R
import com.metatreasures.metatreasures.navigation.Navigation
import com.metatreasures.metatreasures.ui.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun StartMainScreen(navController: NavController, darkTheme: Boolean) {
    var isVisible by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    val backgroundColor = if (darkTheme) {
        AppTheme.colors.backgroundTextFieldColor
    } else {
        AppTheme.colors.backgroundFormColor
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(2000)
            isVisible = false
            delay(250)
            navController.navigate(Navigation.CryptoExperienceScreen.route) {
                popUpTo(Navigation.StartMainScreen.route) { inclusive = true }
            }
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = EnterTransition.None,
        exit = slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(
                durationMillis = 250,
                easing = LinearOutSlowInEasing
            )
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.my_logo)
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(320.dp)
            )
        }
    }
}

@Composable
fun PreviewSplash() {
    AppTheme {
        val navController = rememberNavController()
        StartMainScreen(navController, darkTheme = true)
    }
}
