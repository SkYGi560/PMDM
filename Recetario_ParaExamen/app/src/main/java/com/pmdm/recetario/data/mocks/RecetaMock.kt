package com.pmdm.recetario.data.mocks

data class RecetaMock(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val chef: String,
    val foto: String?,
    val favorita: Boolean = false
)
