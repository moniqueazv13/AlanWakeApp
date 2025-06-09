package br.com.alanwakeapp.domain.repository

import br.com.alanwakeapp.domain.model.GameInfo
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGameInfo(): Flow<Result<GameInfo>>
}