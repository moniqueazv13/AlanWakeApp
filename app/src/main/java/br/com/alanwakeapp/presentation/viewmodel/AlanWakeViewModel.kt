package br.com.alanwakeapp.presentation.viewmodel

import br.com.alanwakeapp.domain.usecase.GetGameInfoUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AlanWakeViewModel(
    private val getGameInfoUseCase: GetGameInfoUseCase
) : ViewModel() {

    private val _gameState = MutableStateFlow<GameState>(GameState.Loading)
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    init {
        loadGameInfo()
    }

    private fun loadGameInfo() {
        getGameInfoUseCase().onEach { result ->
            result.onSuccess { gameInfo ->
                _gameState.value = GameState.Success(gameInfo)
            }
            result.onFailure { error ->
                _gameState.value = GameState.Error(error.message ?: "Erro desconhecido")
            }
        }.launchIn(viewModelScope)
    }
}