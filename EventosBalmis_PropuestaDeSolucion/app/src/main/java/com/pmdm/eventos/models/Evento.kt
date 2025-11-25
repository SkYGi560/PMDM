package com.pmdm.eventos.models

data class Evento(
    val imagen: String? = null,
    val titulo: String = "",
    val fecha: String = "",
    val lugar: String = "",
    val realizado: String = "",
    val precio: Float=0.0f,
    val seguidores: Int= 0
)

