package br.com.alanwakeapp.data

import br.com.alanwakeapp.domain.model.GameInfo
import br.com.alanwakeapp.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GameRepositoryImpl(private val localDataSource: LocalDataSource) : GameRepository {
    override fun getGameInfo(): Flow<Result<GameInfo>> = flow {
        try {
            val gameInfo = localDataSource.getGameInfoFromJson()
            emit(Result.success(gameInfo))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)
}