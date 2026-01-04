package com.pmdm.agenda.data

import com.pmdm.agenda.data.mocks.contacto.ContactoDaoMock
import com.pmdm.agenda.models.Contacto
import javax.inject.Inject

class ContactoRepository @Inject constructor(
    private val dao: ContactoDaoMock
)  {

    fun get(): MutableList<Contacto> = dao.get().map { it.toContacto() }.toMutableList()
    fun get(id: Int): Contacto? = dao.get(id)?.toContacto()
    fun insert(contacto: Contacto) = dao.insert(contacto.toContactoMock())
    fun update(contacto: Contacto) = dao.update(contacto.toContactoMock())
    fun delete(id: Int) = dao.delete(id)

}