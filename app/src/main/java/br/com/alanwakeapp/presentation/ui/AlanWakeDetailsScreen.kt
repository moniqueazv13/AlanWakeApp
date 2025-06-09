package br.com.alanwakeapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.alanwakeapp.presentation.CharacterItemClicked
import coil.compose.AsyncImage

@Composable
fun AlanWakeDetailsScreen(character: CharacterItemClicked) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        GameDetails(character)
    }
}

@Composable
fun GameDetails(character: CharacterItemClicked) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        AsyncImage(
            model = character.imagem,
            contentDescription = "Imagem do Personagem",
            modifier = Modifier
                .size(300.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = character.nome,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Papel: ${character.papel}",
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Descrição: ${character.descricao}",
            fontWeight = FontWeight.SemiBold
        )
    }
}