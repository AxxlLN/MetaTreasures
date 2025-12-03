package com.metatreasures.metatreasures.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.metatreasures.metatreasures.screens.AccountScreen
import com.metatreasures.metatreasures.screens.NewsScreen
import com.metatreasures.metatreasures.screens.SettingsScreen
import com.metatreasures.metatreasures.screens.TransactionScreen
import com.metatreasures.metatreasures.viewmodel.TokenViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.metatreasures.metatreasures.datastore.Prefs

@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val tokenViewModel: TokenViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.TokenScreen.route,
        modifier = modifier
    ) {
        composable(BottomNavItem.TokenScreen.route) {
            val innerNavController = rememberNavController()
            val ctx = androidx.compose.ui.platform.LocalContext.current
            LaunchedEffect(Unit) {
                Prefs.setExperienceShown(ctx)
            }
            TokenNavGraph(innerNavController)
        }
        composable(BottomNavItem.NewsScreen.route) {
            val innerNavController = rememberNavController()
            NewsNavGraph(innerNavController)
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
