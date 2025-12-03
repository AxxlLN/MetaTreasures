package com.metatreasures.metatreasures.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.metatreasures.metatreasures.screens.CryptoExperienceScreen
import com.metatreasures.metatreasures.screens.LoadingScreen
import com.metatreasures.metatreasures.screens.LogInScreen
import com.metatreasures.metatreasures.screens.MainNavigationScreen
import com.metatreasures.metatreasures.screens.SignUpScreen
import com.metatreasures.metatreasures.screens.StartMainScreen
import com.metatreasures.metatreasures.datastore.Prefs
import com.metatreasures.metatreasures.viewmodel.AuthViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    onGoogleSignIn: () -> Unit,
    authViewModel: AuthViewModel
) {

    NavHost(navController = navController, startDestination = Navigation.StartMainScreen.route) {
        composable(Navigation.StartMainScreen.route) {
            StartMainScreen(navController = navController, authViewModel)
        }
        composable(Navigation.LogInScreen.route) {
            LogInScreen(navController, onGoogleSignIn, authViewModel)
        }
        composable(Navigation.SignUpScreen.route) {
            SignUpScreen(navController, onGoogleSignIn)
        }
        composable(Navigation.MainNavigationScreen.route) {
            MainNavigationScreen(navController = navController)
        }
        composable(Navigation.CryptoExperienceScreen.route) {
            CryptoExperienceScreen(navController)
        }
        composable(Navigation.LoadingScreen.route) {
            LoadingScreen(navController)
        }
    }
}
