package com.pmdm.eventos.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pmdm.eventos.ui.features.presentacion_eventos.EventoViewModel
import com.pmdm.eventos.ui.features.presentacion_eventos.PresentacionEventoScreen
import com.pmdm.eventos.ui.theme.PresentacionEventosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PresentacionEventosTheme {
                val viewModel: EventoViewModel = viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PresentacionEventoScreen(
                        modifier = Modifier.padding(innerPadding),
                        dialogoUiState = viewModel.dialogoUiState,
                        onDialogoEvent = viewModel::onDialogoEvent,
                        eventosUiState = viewModel.eventosUiState[viewModel.eventoVisible],
                        onEventosEvent = viewModel::onEventosEvent
                    )

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PresentacionEventosTheme {
        Greeting("Android")
    }
}