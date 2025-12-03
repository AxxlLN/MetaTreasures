package com.metatreasures.metatreasures.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.metatreasures.metatreasures.R
import com.metatreasures.metatreasures.components.BottomSection
import com.metatreasures.metatreasures.components.ExperienceOption
import com.metatreasures.metatreasures.components.GradientBackground
import com.metatreasures.metatreasures.components.TopSection
import com.metatreasures.metatreasures.navigation.Navigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CryptoExperienceScreen(navController: NavController) {
    var isVisible by remember { mutableStateOf(true) }

    fun navigateToLoading() {
        isVisible = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        GradientBackground()

        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopSection()

                BottomSection(
                    onNoviceClick = {
                        isVisible = false
                        navController.navigate(Navigation.LoadingScreen.route) {
                            popUpTo(Navigation.CryptoExperienceScreen.route) { inclusive = true }
                        }
                    },
                    onExperiencedClick = {
                        isVisible = false
                        navController.navigate(Navigation.LoadingScreen.route) {
                            popUpTo(Navigation.CryptoExperienceScreen.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

