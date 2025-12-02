package com.metatreasures.metatreasures.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

data class AppTypography (
    val title: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = AppFontFamily().SFProTextBold,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp
    ),
    val titleMain: TextStyle = TextStyle(
        fontStyle = AppFontFamily().SFProTextBold,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    val errorTitle: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle =  AppFontFamily().SFProTextBold,
        fontWeight =  FontWeight.Bold,
        fontSize = 25.sp
    ),
    val text: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = AppFontFamily().SFProTextMedium,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    val buttonText: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = AppFontFamily().SFProTextBold,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    val textSingUpOrLogIn: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = AppFontFamily().SFProTextMedium,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    val buttonAuthenticationText: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = AppFontFamily().SFProTextMedium,
        fontWeight = FontWeight.Normal,
        textDecoration = TextDecoration.Underline,
        fontSize = 16.sp
    )
)
internal val LocalTypography = staticCompositionLocalOf { AppTypography() }