package com.pmdm.agenda.data

import com.pmdm.agenda.data.mocks.contacto.ContactoDaoMock
import com.pmdm.agenda.models.Contacto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.text.get
import kotlin.text.insert

class ContactoRepository @Inject constructor(
    private val dao: ContactoDaoMock
) {

    suspend fun get(): MutableList<Contacto> = withContext(Dispatchers.IO) {
        retardoAleatorio()
        dao.get().map { it.toContacto() }.toMutableList()
    }

    suspend fun get(id: Int): Contacto? = withContext(Dispatchers.IO) {
        retardoAleatorio()
        dao.get(id)?.toContacto()
    }

    suspend fun insert(contacto: Contacto) = withContext(Dispatchers.IO) {
        retardoAleatorio()
        dao.insert(contacto.toContactoMock())
    }

    suspend fun update(contacto: Contacto) = withContext(Dispatchers.IO) {
        retardoAleatorio()
        dao.update(contacto.toContactoMock())
    }

    suspend fun delete(id: Int) = withContext(Dispatchers.IO){
        retardoAleatorio()
        dao.delete(id)
    }
    private fun retardoAleatorio() = Thread.sleep((Math.random() * 1000).toLong())

}