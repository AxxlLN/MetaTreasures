package com.metatreasures.metatreasures.navigation

sealed class TokenNavItem(val route: String) {
    object TokenScreen : TokenNavItem("token")
    object TokenDetailScreen : TokenNavItem("token_detail")

    object TokenWalletScreen : TokenNavItem("token_detail")
}