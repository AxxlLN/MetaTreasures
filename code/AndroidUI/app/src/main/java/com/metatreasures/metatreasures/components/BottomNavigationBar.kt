package com.metatreasures.metatreasures.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.metatreasures.metatreasures.navigation.BottomNavItem
import com.metatreasures.metatreasures.ui.theme.AppTheme

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        BottomNavItem.NewsScreen,
        BottomNavItem.TransactionScreen,
        BottomNavItem.TokenScreen,
        BottomNavItem.SettingsScreen,
        BottomNavItem.AccountScreen
    )

    NavigationBar(containerColor = AppTheme.colors.backgroundMainScreenColor) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(painterResource(id = item.icon), contentDescription = item.title)
                },
                label = { Text(text = item.title,
                    fontSize = 10.sp,
                    maxLines = 1) }
            )
        }
    }
}

