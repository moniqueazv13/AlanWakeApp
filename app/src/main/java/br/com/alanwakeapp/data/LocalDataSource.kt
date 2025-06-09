package br.com.alanwakeapp.data

import android.content.res.AssetManager
import br.com.alanwakeapp.domain.model.GameInfo
import kotlinx.serialization.json.Json

class LocalDataSource(private val assetManager: AssetManager) {
    fun getGameInfoFromJson(): GameInfo {
        val jsonString = assetManager.open("alan_wake.json").bufferedReader().use { it.readText() }
        val json = Json { ignoreUnknownKeys = true }
        return json.decodeFromString<GameInfo>(jsonString)
    }
}