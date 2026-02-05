package com.pmdm.agenda.data

import com.pmdm.agenda.data.room.ContactoDao
import com.pmdm.agenda.models.Contacto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContactoRepository @Inject constructor(
    private val dao: ContactoDao
) {
    suspend fun get(): List<Contacto> = withContext(Dispatchers.IO) {
        dao.get().map { it.toContacto() }.toList()
    }
    suspend fun get(id: Int): Contacto  = withContext(Dispatchers.IO) {
        val dato = dao.get(id)
        dato!!.toContacto()
    }
    suspend fun insert(contacto: Contacto)  = withContext(Dispatchers.IO) {
        dao.insert(contacto.toContactoEntity())
    }
    suspend fun update(contacto: Contacto) = withContext(Dispatchers.IO) {
        dao.update(contacto.toContactoEntity())
    }
    suspend fun delete(id: Int)  = withContext(Dispatchers.IO) {
        dao.delete(dao.get(id))
    }
}