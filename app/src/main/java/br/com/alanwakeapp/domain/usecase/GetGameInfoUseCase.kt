package br.com.alanwakeapp.domain.usecase

import br.com.alanwakeapp.domain.model.GameInfo
import br.com.alanwakeapp.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow

class GetGameInfoUseCase(private val repository: GameRepository) {
    operator fun invoke(): Flow<Result<GameInfo>> {
        return repository.getGameInfo()
    }
}