package com.metatreasures.metatreasures.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metatreasures.metatreasures.data.TokenRepository
import com.metatreasures.metatreasures.data.dto.TokenDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TokenViewModel(private val repo: TokenRepository) : ViewModel() {
    private val _tokens = MutableStateFlow<List<TokenDto>>(emptyList())
    val tokens: StateFlow<List<TokenDto>> = _tokens

    fun loadTokens() {
        viewModelScope.launch {
            val result = repo.fetchTokens()
            result.onSuccess { list ->
                Log.d("TokenViewModel", "Получено ${list.size} токенов")
                _tokens.value = list
            }.onFailure { e ->
                Log.e("TokenViewModel", "Ошибка загрузки токенов", e)
            }
        }
    }
}
