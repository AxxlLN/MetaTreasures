package com.metatreasures.metatreasures.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.metatreasures.metatreasures.components.ThemeCard
import com.metatreasures.metatreasures.datastore.AppThemeEnum
import com.metatreasures.metatreasures.viewmodel.SettingsViewModel

import androidx.compose.ui.res.stringResource
import com.metatreasures.metatreasures.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val currentTheme by viewModel.themeFlow.collectAsState(initial = AppThemeEnum.SYSTEM)

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.settings_title)) }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.theme_choice),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            ThemeCard(
                title = stringResource(R.string.theme_system),
                selected = currentTheme == AppThemeEnum.SYSTEM,
                onClick = { viewModel.saveTheme(AppThemeEnum.SYSTEM) }
            )
            ThemeCard(
                title = stringResource(R.string.theme_light),
                selected = currentTheme == AppThemeEnum.LIGHT,
                onClick = { viewModel.saveTheme(AppThemeEnum.LIGHT) }
            )
            ThemeCard(
                title = stringResource(R.string.theme_dark),
                selected = currentTheme == AppThemeEnum.DARK,
                onClick = { viewModel.saveTheme(AppThemeEnum.DARK) }
            )
        }
    }
}

