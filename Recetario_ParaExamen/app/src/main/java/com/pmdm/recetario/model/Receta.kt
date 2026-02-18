package com.pmdm.recetario.model

data class Receta(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val chef: String,
    val foto: String?,
    val favorita: Boolean
)