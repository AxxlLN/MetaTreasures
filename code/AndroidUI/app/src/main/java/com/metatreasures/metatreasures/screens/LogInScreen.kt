package com.metatreasures.metatreasures.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.metatreasures.metatreasures.components.*
import com.metatreasures.metatreasures.navigation.Navigation
import com.metatreasures.metatreasures.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    navController: NavController,
    onGoogleSignIn: () -> Unit,
    viewModel: AuthViewModel
) {
    var input by remember { mutableStateOf("") }
    var agreed by remember { mutableStateOf(true) }
    val showTermsSheet = remember { mutableStateOf(false) }

    val state by viewModel.state.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(24.dp)
    ) {
        CloseButton(navController)
        WelcomeHeader()

        Column(modifier = Modifier.align(Alignment.Center)) {
            EmailInput(input) { input = it }
            Spacer(Modifier.height(24.dp))
            TermsCheckbox(agreed, { agreed = it }) { showTermsSheet.value = true }
            Spacer(Modifier.height(16.dp))
            ContinueButton { if (agreed) viewModel.login(input, "dummyPassword") }
            Spacer(Modifier.height(16.dp))
            DividerOr()
            Spacer(Modifier.height(16.dp))
            GoogleButton { onGoogleSignIn() }
            Spacer(Modifier.height(24.dp))
            SignUpText(navController)

            AnimatedVisibility(
                visible = state.loading,
                enter = fadeIn(animationSpec = tween(500, easing = FastOutSlowInEasing)),
                exit = fadeOut(animationSpec = tween(500, easing = FastOutSlowInEasing))
            ) {
                CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            }

            state.error?.let {
                Text(text = it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
            }
        }
    }

    TermsSheet(showTermsSheet.value, { showTermsSheet.value = false }, sheetState)

    LaunchedEffect(state.success) {
        if (state.success) {
            delay(300)
            navController.navigate(Navigation.MainNavigationScreen.route) {
                popUpTo(Navigation.LogInScreen.route) { inclusive = true }
            }
        }
    }
}


@Preview
@Composable
private fun DefaultPreview() {
    val navController = rememberNavController()
    LogInScreen(navController = navController, onGoogleSignIn = { Unit }, viewModel())
}
