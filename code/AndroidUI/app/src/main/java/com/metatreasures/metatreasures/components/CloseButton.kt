package com.metatreasures.metatreasures.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.metatreasures.metatreasures.navigation.Navigation

@Composable
fun CloseButton(navController: NavController, modifier: Modifier = Modifier) {
    IconButton(
        onClick = {
            navController.navigate(Navigation.MainNavigationScreen.route) {
                popUpTo(Navigation.LogInScreen.route) { inclusive = true }
            }
        },
        modifier = modifier
    ) {
        Icon(Icons.Default.Close, contentDescription = "Закрыть", tint = Color.Black)
    }
}