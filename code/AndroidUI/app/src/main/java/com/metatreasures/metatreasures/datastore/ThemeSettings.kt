package com.metatreasures.metatreasures.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

enum class AppThemeEnum { LIGHT, DARK, SYSTEM }

private val Context.dataStore by preferencesDataStore("theme_preferences")

class ThemeSettings(private val context: Context) {

    companion object {
        private val THEME_KEY = stringPreferencesKey("app_theme")
    }

    val themeFlow: Flow<AppThemeEnum> = context.dataStore.data
        .map { preferences ->
            val theme = preferences[THEME_KEY] ?: AppThemeEnum.SYSTEM.name
            AppThemeEnum.valueOf(theme)
        }

    suspend fun saveTheme(theme: AppThemeEnum) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.name
        }
    }
}
