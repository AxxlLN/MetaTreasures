package com.metatreasures.metatreasures.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.metatreasures.metatreasures.components.BottomNavigationBar
import com.metatreasures.metatreasures.navigation.Navigation
import com.metatreasures.metatreasures.viewmodel.TokenViewModel
import com.metatreasures.metatreasures.data.dto.TokenDto
import com.metatreasures.metatreasures.navigation.BottomNavigationGraph

@Composable
fun MainNavigationScreen(
    navController: NavController,
    darkTheme: Boolean,
    tokenViewModel: TokenViewModel
) {
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(bottomNavController) }
    ) { innerPadding ->
        BottomNavigationGraph(
            navController = bottomNavController,
            tokenViewModel = tokenViewModel
        )
    }
}

