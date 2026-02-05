package com.pmdm.tienda.models


data class Direccion(
    val calle: String?,
    val ciudad: String?,
    val pais: String?,
    val codigoPostal: String?)
{
    override fun toString(): String {
        return "$calle  $ciudad ($codigoPostal)"
    }
}

data class Cliente(
    val dni: String="",
    val nombre: String="",
    val apellidos: String="",
    val direccion: Direccion?=null,
)

