package com.metatreasures.metatreasures.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.metatreasures.metatreasures.components.BottomNavigationBar
import com.metatreasures.metatreasures.viewmodel.TokenViewModel
import com.metatreasures.metatreasures.navigation.BottomNavigationGraph
import androidx.hilt.navigation.compose.hiltViewModel
import com.metatreasures.metatreasures.datastore.Prefs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigationScreen(
    navController: NavController,
) {
    val bottomNavController = rememberNavController()

    val context = androidx.compose.ui.platform.LocalContext.current

    LaunchedEffect(Unit) {
        Prefs.setExperienceShown(context)
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(bottomNavController) }
    ) { innerPadding ->
        BottomNavigationGraph(
            navController = bottomNavController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
