package com.pmdm.examennoviembre2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.pmdm.examennoviembre2025.ui.features.JuegoScreen
import com.pmdm.examennoviembre2025.ui.features.JuegoViewModel
import com.pmdm.examennoviembre2025.ui.theme.ExamenNoviembre2025Theme
import dagger.hilt.android.AndroidEntryPoint

import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenNoviembre2025Theme(){
                val juegoViewModel: JuegoViewModel by viewModels()
                Scaffold { paddingValues ->
                    JuegoScreen(
                        modifier = Modifier.padding(paddingValues),
                        juegoUiState = juegoViewModel.juegoUiState,
                        inicioEvent = juegoViewModel::onInicioEvent,
                        preguntaEvent = juegoViewModel::onPreguntaEvent,
                        validacion = juegoViewModel.validacion
                    )
                }
            }
        }
    }
}

