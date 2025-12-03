package com.metatreasures.metatreasures.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.metatreasures.metatreasures.R
import com.metatreasures.metatreasures.components.GreetingSection
import com.metatreasures.metatreasures.components.SearchTokensField
import com.metatreasures.metatreasures.components.TokensCard
import com.metatreasures.metatreasures.components.TopPanel
import com.metatreasures.metatreasures.datastore.Prefs
import com.metatreasures.metatreasures.viewmodel.TokenViewModel

@Composable
fun TokenScreen(
    navController: NavController,
    isLoggedIn: Boolean = false,
    tokenViewModel: TokenViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        Prefs.setExperienceShown(context)
        tokenViewModel.loadTokens()
    }

    var visible by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val tokens by tokenViewModel.tokens.collectAsState()

    LaunchedEffect(Unit) { visible = true }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(dimensionResource(R.dimen.screen_padding))
    ) {
        TopPanel(navController, isLoggedIn)

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))

        SearchTokensField(searchQuery) { searchQuery = it }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))

        if (!isLoggedIn) GreetingSection(navController)

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_medium)))

        TokensCard(
            visible = visible,
            tokens = tokens,
            searchQuery = searchQuery,
            expanded = expanded,
            onExpandToggle = { expanded = !expanded },
            navController = navController
        )
    }
}
