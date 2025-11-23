package com.pmdm.eventos.ui.features.presentacion_eventos

data class EventoUiState(
    val imagen: String? = null,
    val titulo: String = "",
    val fecha: String = "",
    val lugar: String = "",
    val realizado: String = "",
    val precio: Float=0.0f,
    val seguidores: Int= 0,
    val siguiendo: Boolean = false
)

