package com.pmdm.examennoviembre2025.ui.features.pregunta

data class PreguntaUiState(
    val pregunta: String = "",
    val opcionCorrecta: Int = 0,
    val imagenUrl: String = "",
    val tipoPregunta: Int = 0,
    val respuestas: List<String> = listOf<String>(),
    val estaSeleccionada: Boolean = false,
    val mostrarPuntos: Boolean = false,
    val textoDialogo: String = "",
)