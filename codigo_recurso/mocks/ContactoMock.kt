package com.pmdm.navegacionlateral.data.mocks



enum class TipoContactoMock {
    Amigos, Coro, Familia, Trabajo, Vecinos
}
data class ContactoMock(val id: Int, val nombre: String, val tipo: TipoContactoMock)
