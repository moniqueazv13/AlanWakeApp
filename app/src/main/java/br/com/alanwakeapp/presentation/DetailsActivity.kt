package br.com.alanwakeapp.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import br.com.alanwakeapp.presentation.ui.AlanWakeDetailsScreen
import br.com.alanwakeapp.presentation.ui.theme.AlanWakeAppTheme

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlanWakeAppTheme {
                val character =
                    intent.getSerializableExtra("EXTRA_CHARACTER", CharacterItemClicked::class.java)
                character?.let {
                    AlanWakeDetailsScreen(it)
                } ?: run {
                    Text("Erro: Personagem não encontrado.")
                }
            }
        }
    }
}