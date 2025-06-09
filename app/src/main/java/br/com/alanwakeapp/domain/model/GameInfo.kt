package br.com.alanwakeapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GameInfo(
    val jogo: Game
)

@Serializable
data class Game(
    val titulo: String,
    val informacoes_gerais: GeneralInfo,
    val enredo_e_universo: Universe,
    val personagens: List<Character>,
    val galeria_de_imagens: List<ImageGalleryItem>
)

@Serializable
data class GeneralInfo(
    val desenvolvedora: String,
    val imagem_capa: String
)

@Serializable
data class Universe(
    val sinopse: String,
    val cenario: Scenery
)

@Serializable
data class Scenery(
    val nome: String,
    val descricao: String
)

@Serializable
data class Character(
    val nome: String,
    val papel: String,
    val descricao: String,
    val imagem: String
)

@Serializable
data class ImageGalleryItem(
    val titulo: String,
    val url: String
)