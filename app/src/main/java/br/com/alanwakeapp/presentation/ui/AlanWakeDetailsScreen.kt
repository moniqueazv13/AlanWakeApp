package br.com.alanwakeapp.presentation.ui

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.alanwakeapp.presentation.CharacterItemClicked
import br.com.alanwakeapp.presentation.viewmodel.AlanWakeDetailsViewModel
import br.com.alanwakeapp.presentation.viewmodel.UiAction
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun AlanWakeDetailsScreen(
    character: CharacterItemClicked,
    viewModel: AlanWakeDetailsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.uiAction.collectAsState(initial = null)

    LaunchedEffect(state) {
        if (state is UiAction.GoBack) {
            (context as? Activity)?.finish()
        }
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        GameDetails(character)
    }
}

@Composable
fun GameDetails(
    character: CharacterItemClicked,
    viewModel: AlanWakeDetailsViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {

        IconButton(onClick = { viewModel.goBack() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Voltar"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
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
