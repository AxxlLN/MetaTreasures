package com.metatreasures.metatreasures.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.metatreasures.metatreasures.screens.TokenDetailScreen
import com.metatreasures.metatreasures.screens.TokenScreen
import com.metatreasures.metatreasures.viewmodel.AuthViewModel
import com.metatreasures.metatreasures.viewmodel.TokenViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.metatreasures.metatreasures.screens.TokenWalletScreen

@Composable
fun TokenNavGraph(
    navController: NavHostController
) {
    val tokenViewModel: TokenViewModel = hiltViewModel()
    val authViewModel: AuthViewModel = hiltViewModel()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    NavHost(
        navController = navController,
        startDestination = TokenNavItem.TokenScreen.route
    ) {
        composable(TokenNavItem.TokenScreen.route) {
            TokenScreen(navController, isLoggedIn, tokenViewModel)
        }

        composable("${TokenNavItem.TokenDetailScreen.route}/{tokenId}") { backStackEntry ->
            val tokenId = backStackEntry.arguments?.getString("tokenId")
            TokenDetailScreen(navController, tokenId)
        }
        composable(TokenNavItem.TokenWalletScreen.route) {
            TokenWalletScreen(navController)
        }
    }
}
