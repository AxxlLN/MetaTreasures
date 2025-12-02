package com.metatreasures.metatreasures.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.metatreasures.metatreasures.data.TokenRepository
import com.metatreasures.metatreasures.viewmodel.TokenViewModel

class TokenViewModelFactory(
    private val repo: TokenRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TokenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TokenViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}