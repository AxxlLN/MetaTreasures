package com.metatreasures.metatreasures.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.metatreasures.metatreasures.R

@Composable
fun BottomSection(
    onNoviceClick: () -> Unit,
    onExperiencedClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExperienceOption(
            title = stringResource(R.string.novice_title),
            description = stringResource(R.string.novice_description),
            onClick = onNoviceClick,
            titleStyle = MaterialTheme.typography.titleMedium,
            descriptionStyle = MaterialTheme.typography.bodyMedium,
            titleColor = MaterialTheme.colorScheme.primary,
            descriptionColor = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExperienceOption(
            title = stringResource(R.string.experienced_title),
            description = stringResource(R.string.experienced_description),
            onClick = onExperiencedClick,
            titleStyle = MaterialTheme.typography.titleMedium,
            descriptionStyle = MaterialTheme.typography.bodyMedium,
            titleColor = MaterialTheme.colorScheme.primary,
            descriptionColor = MaterialTheme.colorScheme.onSurface
        )
    }
}
