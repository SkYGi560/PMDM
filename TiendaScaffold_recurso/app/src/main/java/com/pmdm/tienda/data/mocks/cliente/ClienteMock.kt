package com.pmdm.tienda.data.mocks.cliente



data class Direccion(
    val calle: String?,
    val ciudad: String?,
    val codigoPostal: String?)
{
    override fun toString(): String {
        return "$calle  $ciudad ($codigoPostal)"
    }
}

data class ClienteMock(
    val dni: String,
    val correo: String,
    val nombre: String,
    val telefono: String,
    val direccion: Direccion?,
    val favoritos:MutableList<Int>,
)

