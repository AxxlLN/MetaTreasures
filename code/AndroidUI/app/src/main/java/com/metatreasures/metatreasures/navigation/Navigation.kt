package com.metatreasures.metatreasures.navigation

sealed class Navigation(val route: String) {
    object SignUpScreen : Navigation("sing_up_screen")
    object LogInScreen : Navigation("log_in_screen")
    object MainNavigationScreen : Navigation("main_navigation_screen")
    object StartMainScreen : Navigation("start_main_screen")

    object CryptoExperienceScreen: Navigation("crypto_experience_screen")

    object LoadingScreen: Navigation("loading_screen")
}
