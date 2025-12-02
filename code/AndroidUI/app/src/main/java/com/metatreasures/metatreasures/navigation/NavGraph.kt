package com.metatreasures.metatreasures.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.metatreasures.metatreasures.screens.CryptoExperienceScreen
import com.metatreasures.metatreasures.screens.LoadingScreen
import com.metatreasures.metatreasures.screens.LogInScreen
import com.metatreasures.metatreasures.screens.MainNavigationScreen
import com.metatreasures.metatreasures.screens.SignUpScreen
import com.metatreasures.metatreasures.screens.StartMainScreen
import com.metatreasures.metatreasures.viewmodel.AuthViewModel
import com.metatreasures.metatreasures.viewmodel.TokenViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    darkTheme: Boolean,
    onGoogleSignIn: () -> Unit,
    authViewModel: AuthViewModel,
    tokenViewModel: TokenViewModel
) {
    NavHost(navController = navController, startDestination = Navigation.StartMainScreen.route) {
        composable(Navigation.StartMainScreen.route) {
            StartMainScreen(navController = navController, darkTheme = darkTheme)
        }
        composable(Navigation.LogInScreen.route) {
            LogInScreen(navController, onGoogleSignIn, authViewModel)
        }
        composable(Navigation.SignUpScreen.route) {
            SignUpScreen(navController, onGoogleSignIn, authViewModel)
        }
        composable(Navigation.MainNavigationScreen.route) {
            MainNavigationScreen(navController = navController, darkTheme = darkTheme, tokenViewModel)
        }
        composable(Navigation.CryptoExperienceScreen.route) {
            CryptoExperienceScreen(navController)
        }
        composable(Navigation.LoadingScreen.route) {
            LoadingScreen(navController)
        }
    }
}
