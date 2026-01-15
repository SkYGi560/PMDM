package com.pmdm.navegacionlateral.data.mocks

import com.pmdm.navegacionlateral.models.TipoContacto
import kotlin.random.Random

class ContactoDaoMock {

    var listaTodos = mutableListOf<ContactoMock>()
    var listaAmigos = listOf<ContactoMock>()
    var listaCoro = listOf<ContactoMock>()
    var listaTrabajo = listOf<ContactoMock>()
    var listaFamilia = listOf<ContactoMock>()
    var listaVecinos = listOf<ContactoMock>()

    init {
        for (i in 1..50) {
            when (Random.nextInt(1, 6)) {
                1 -> listaTodos.add(ContactoMock(i, "Amigo $i", TipoContactoMock.Amigos))
                2 -> listaTodos.add(ContactoMock(i, "Coro $i", TipoContactoMock.Coro))
                3 -> listaTodos.add(ContactoMock(i, "Trabajo $i", TipoContactoMock.Trabajo))
                4 -> listaTodos.add(ContactoMock(i, "Familia $i", TipoContactoMock.Familia))
                else -> listaTodos.add(ContactoMock(i, "Vecino $i", TipoContactoMock.Vecinos))
            }

        }
        listaAmigos = listaTodos.filter { it.nombre.contains("Amigo") }
        listaCoro = listaTodos.filter { it.nombre.contains("Coro") }
        listaTrabajo = listaTodos.filter { it.nombre.contains("Trabajo") }
        listaFamilia = listaTodos.filter { it.nombre.contains("Familia") }
        listaVecinos = listaTodos.filter { it.nombre.contains("Vecino") }
    }

    fun getTodos() = listaTodos
    fun getAmigos() = listaAmigos
    fun getCoro() = listaCoro
    fun getTrabajo() = listaTrabajo
    fun getFamilia() = listaFamilia
    fun getVecinos() = listaVecinos

    fun getContacto(id: Int) = listaTodos.find { it.id == id }
}