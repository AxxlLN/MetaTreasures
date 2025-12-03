package com.metatreasures.metatreasures.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.metatreasures.metatreasures.navigation.Navigation
import com.metatreasures.metatreasures.R

@Composable
fun SignUpText(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(Navigation.SignUpScreen.route) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.sign_up_text),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
