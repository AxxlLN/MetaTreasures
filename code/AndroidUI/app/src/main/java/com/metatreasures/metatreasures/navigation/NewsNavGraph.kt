package com.metatreasures.metatreasures.navigation

import com.metatreasures.metatreasures.screens.NewsDetailScreen
import com.metatreasures.metatreasures.screens.NewsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NewsNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NewsNavItem.NewsScreen.route
    ) {
        composable(NewsNavItem.NewsScreen.route) {
            NewsScreen(navController)
        }

        composable("${NewsNavItem.NewsDetailScreen.route}/{newsId}") { backStackEntry ->
            val newsId = backStackEntry.arguments?.getString("newsId")
            NewsDetailScreen(navController, newsId)
        }
    }
}
