package com.pmdm.menulateral.models

enum class TipoContacto {
    Amigos, Coro, Familia, Trabajo, Vecinos
}

data class Contacto(
    val id: Int,
    val nombre: String,
    val tipo: TipoContacto
)
