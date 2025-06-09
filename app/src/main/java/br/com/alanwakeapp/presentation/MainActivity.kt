package br.com.alanwakeapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.alanwakeapp.presentation.ui.AlanWakeScreen
import br.com.alanwakeapp.presentation.ui.theme.AlanWakeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlanWakeAppTheme {
                AlanWakeScreen()
            }
        }
    }
}