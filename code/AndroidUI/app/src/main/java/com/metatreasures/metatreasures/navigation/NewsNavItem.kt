package com.metatreasures.metatreasures.navigation

sealed class NewsNavItem(val route: String) {
    object NewsScreen : NewsNavItem("news")
    object NewsDetailScreen : NewsNavItem("detail_news")
}