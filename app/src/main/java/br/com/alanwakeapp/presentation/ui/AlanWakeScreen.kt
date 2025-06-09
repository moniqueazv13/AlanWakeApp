package br.com.alanwakeapp.presentation.ui

import android.content.Intent
import androidx.compose.foundation.clickable
import br.com.alanwakeapp.domain.model.GameInfo
import br.com.alanwakeapp.presentation.viewmodel.AlanWakeViewModel
import br.com.alanwakeapp.presentation.viewmodel.GameState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alanwakeapp.domain.model.Character
import br.com.alanwakeapp.presentation.CharacterItemClicked
import br.com.alanwakeapp.presentation.DetailsActivity
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun AlanWakeScreen(viewModel: AlanWakeViewModel = koinViewModel()) {
    val state by viewModel.gameState.collectAsState()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        when (val currentState = state) {
            is GameState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is GameState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Erro ao carregar: ${currentState.message}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            is GameState.Success -> {
                GameContent(gameInfo = currentState.data)
            }
        }
    }
}

@Composable
fun GameContent(gameInfo: GameInfo) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        val game = gameInfo.jogo

        item {
            Text(
                text = game.titulo,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Desenvolvido por: ${game.informacoes_gerais.desenvolvedora}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            AsyncImage(
                model = game.informacoes_gerais.imagem_capa,
                contentDescription = "Capa do Jogo",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }


        item {
            SectionTitle("Sinopse")
            Text(text = game.enredo_e_universo.sinopse, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
        }


        item {
            SectionTitle("Personagens")
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(game.personagens) { character ->
                    CharacterCard(
                        character = character,
                        onClick = {
                            val intent = Intent(context, DetailsActivity::class.java).apply {
                                putExtra(
                                    "EXTRA_CHARACTER",
                                    CharacterItemClicked(
                                        character.nome,
                                        character.papel,
                                        character.descricao,
                                        character.imagem
                                    )
                                )
                            }
                            context.startActivity(intent)
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            SectionTitle("Galeria")
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(game.galeria_de_imagens) { imageItem ->
                    Card(modifier = Modifier.size(250.dp)) {
                        AsyncImage(
                            model = imageItem.url,
                            contentDescription = imageItem.titulo,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = imageItem.titulo,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun CharacterCard(character: Character, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = character.imagem,
                contentDescription = character.nome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = character.nome, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = character.papel, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}