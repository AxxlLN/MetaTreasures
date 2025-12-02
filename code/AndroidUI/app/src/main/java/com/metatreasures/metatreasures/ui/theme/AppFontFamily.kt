package com.metatreasures.metatreasures.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.FontStyle
import com.metatreasures.metatreasures.R

data class AppFontFamily(
    val SFProTextBold: FontStyle =  FontStyle(R.font.sfprotext_bold),
    val SFProTextMedium: FontStyle = FontStyle(R.font.sfprotext_medium),
)

internal val LocalFontFamily = staticCompositionLocalOf { AppFontFamily() }