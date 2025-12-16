package com.pmdm.examennoviembre2025.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.pmdm.examennoviembre2025.models.Pregunta
import com.pmdm.examennoviembre2025.ui.features.JuegoUiState
import com.pmdm.examennoviembre2025.ui.features.JuegoViewModel
import com.pmdm.examennoviembre2025.ui.features.inicio.Inicio
import com.pmdm.examennoviembre2025.ui.features.pregunta.PreguntaScreen
import com.pmdm.examennoviembre2025.ui.theme.ExamenNoviembre2025Theme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenNoviembre2025Theme {
                val juegoViewModel: JuegoViewModel by viewModels()
                Scaffold { paddingValues ->
                    if(!juegoViewModel.mostrarPreguntas){
                        Inicio(
                            modifier = Modifier.Companion.padding(paddingValues = paddingValues),
                            juegoUiState = JuegoUiState(juegoViewModel.datosInicioUiState,juegoViewModel.datosPreguntaUiState),
                            validacion = juegoViewModel.validacionDatosInicioUiState,
                            onInicioEvent = {juegoViewModel::onInicioEvent}
                        )
                    }
                    else{
                        PreguntaScreen(
                            juegoViewModel.datosPreguntaUiState,
                            juegoViewModel::onPreguntasEvent
                        )
                    }

                }
            }
        }
    }
}