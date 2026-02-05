package com.pmdm.navegacionlateral.data.mocks

import com.pmdm.navegacionlateral.models.Contacto
import com.pmdm.navegacionlateral.models.TipoContacto
import javax.inject.Inject

class ContactoRepository @Inject constructor(
    private val contactoDaoMock: ContactoDaoMock
) {
    fun getTodos() = contactoDaoMock.getTodos().toContactos().toList()
    fun getAmigos() = contactoDaoMock.getAmigos().toMutableList().toContactos()
    fun getCoro() = contactoDaoMock.getCoro().toMutableList().toContactos()
    fun getTrabajo() = contactoDaoMock.getTrabajo().toMutableList().toContactos()
    fun getFamilia() = contactoDaoMock.getFamilia().toMutableList().toContactos()
    fun getVecinos() = contactoDaoMock.getVecinos().toMutableList().toContactos()
    fun getTipoContacto(id: Int) = contactoDaoMock.getContacto(id)?.tipo

    fun ContactoMock.toContacto() = Contacto(id, nombre, tipo.toTipoContacto())
    fun MutableList<ContactoMock>.toContactos()= map { it.toContacto() }

    fun TipoContactoMock.toTipoContacto() =
        when (this) {
            TipoContactoMock.Coro -> TipoContacto.Coro
            TipoContactoMock.Amigos ->  TipoContacto.Amigos
            TipoContactoMock.Familia ->  TipoContacto.Familia
            TipoContactoMock.Trabajo ->  TipoContacto.Trabajo
            TipoContactoMock.Vecinos ->  TipoContacto.Vecinos
        }
}