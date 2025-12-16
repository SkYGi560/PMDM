package com.pmdm.examennoviembre2025.ui.features

import com.pmdm.examennoviembre2025.ui.features.inicio.InicioUiState
import com.pmdm.examennoviembre2025.ui.features.pregunta.PreguntaUiState

data class JuegoUiState(
    val inicioUiState: InicioUiState,
    val preguntaUiState: PreguntaUiState
)
