package com.metatreasures.metatreasures.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.metatreasures.metatreasures.screens.AccountScreen
import com.metatreasures.metatreasures.screens.NewsScreen
import com.metatreasures.metatreasures.screens.SettingsScreen
import com.metatreasures.metatreasures.screens.TokenScreen
import com.metatreasures.metatreasures.screens.TransactionScreen
import com.metatreasures.metatreasures.viewmodel.TokenViewModel

@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
    tokenViewModel: TokenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.TokenScreen.route
    ) {
        composable(BottomNavItem.TokenScreen.route) {
            TokenScreen(navController, tokenViewModel)
        }
        composable(BottomNavItem.NewsScreen.route) {
            NewsScreen(navController)
        }
        composable(BottomNavItem.TransactionScreen.route) {
            TransactionScreen(navController)
        }
        composable(BottomNavItem.SettingsScreen.route) {
            SettingsScreen(navController)
        }
        composable(BottomNavItem.AccountScreen.route) {
            AccountScreen(navController)
        }
    }
}

