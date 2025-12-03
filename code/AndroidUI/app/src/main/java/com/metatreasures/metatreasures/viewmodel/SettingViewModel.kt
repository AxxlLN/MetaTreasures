package com.metatreasures.metatreasures.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metatreasures.metatreasures.datastore.AppThemeEnum
import com.metatreasures.metatreasures.datastore.ThemeSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themeSettings: ThemeSettings
) : ViewModel() {

    val themeFlow = themeSettings.themeFlow

    fun saveTheme(theme: AppThemeEnum) {
        viewModelScope.launch {
            themeSettings.saveTheme(theme)
        }
    }
}
