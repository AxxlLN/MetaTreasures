package com.metatreasures.metatreasures.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimensions (
    val buttonPadding: Dp = 30.dp,
    val textViewPadding: Dp = 30.dp,
    val verticalElementsPadding: Dp = 10.dp,
    val configurationElementsPadding: Dp = 5.dp,

    val smallButtonCornerRadius: Dp = 20.dp,
    val mainButtonCornerRadius: Dp = 15.dp,

    )
internal val LocalDimensions = staticCompositionLocalOf { AppDimensions() }