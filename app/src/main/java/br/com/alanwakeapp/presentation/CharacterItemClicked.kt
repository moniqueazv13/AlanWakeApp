package br.com.alanwakeapp.presentation

import java.io.Serializable

data class CharacterItemClicked(
    val nome: String,
    val papel: String,
    val descricao: String,
    val imagem: String
) : Serializable