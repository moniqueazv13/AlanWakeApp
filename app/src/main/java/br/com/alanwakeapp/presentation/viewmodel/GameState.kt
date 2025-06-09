package br.com.alanwakeapp.presentation.viewmodel

import br.com.alanwakeapp.domain.model.GameInfo

sealed class GameState {
    data object Loading : GameState()
    data class Success(val data: GameInfo) : GameState()
    data class Error(val message: String) : GameState()
}