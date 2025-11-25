package com.pmdm.examennoviembre2025.ui.features

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.pmdm.examennoviembre2025.ui.features.inicio.Inicio
import com.pmdm.examennoviembre2025.ui.features.inicio.InicioEvent
import com.pmdm.examennoviembre2025.ui.features.pregunta.Pregunta
import com.pmdm.examennoviembre2025.ui.features.pregunta.PreguntaEvent


@Composable
fun JuegoScreen(
    modifier: Modifier = Modifier,
    juegoUiState: JuegoUiState,
    validacion: Validacion,
    inicioEvent: (InicioEvent) -> Unit,
    preguntaEvent: (PreguntaEvent) -> Unit
) {
    Box(modifier = modifier) {
        if (juegoUiState.modoInicio) {
            Inicio(juegoUiState, validacion, inicioEvent)
        } else Pregunta(juegoUiState = juegoUiState, preguntaEvent = preguntaEvent)
    }
}


