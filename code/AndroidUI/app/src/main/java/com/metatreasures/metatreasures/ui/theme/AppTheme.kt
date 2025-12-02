package com.metatreasures.metatreasures.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

object AppTheme {

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
    val dimensions: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
    val fontFamily: AppFontFamily
        @Composable
        @ReadOnlyComposable
        get() = LocalFontFamily.current
}

@Composable
fun AppTheme(
    typography: AppTypography = AppTheme.typography,
    dimensions: AppDimensions = AppTheme.dimensions,
    fontFamily: AppFontFamily = AppTheme.fontFamily,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors()
    } else {
        lightColors()
    }

    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimensions provides dimensions,
        LocalFontFamily provides fontFamily,
        LocalTypography provides typography

    ) {
        content()
    }
}