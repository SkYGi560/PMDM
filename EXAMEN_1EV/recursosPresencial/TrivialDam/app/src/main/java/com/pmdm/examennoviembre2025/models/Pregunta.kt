package com.pmdm.examennoviembre2025.models

data class Pregunta (
    val pregunta: String = "",
    val respuestas: List<String> = listOf<String>(),
    val opcionCorrecta: Int = 0,
    val imagenUrl: String = "",
    val tipoPregunta: Int = 0
)