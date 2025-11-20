package org.ejercicio5.data

import org.ejercicio5.data.mocks.usuarios.UsuarioDaoMock
import org.ejercicio5.data.mocks.usuarios.UsuarioMock
import org.ejercicio5.models.Usuario

class UsuarioRepository {
    private val dao = UsuarioDaoMock()

    fun get(): MutableList<Usuario> = dao.get().map { um ->
        um.toUsuario()
    }.toMutableList()

    fun get(login: String): Usuario? =
        dao.get(login)?.toUsuario()

    fun insert(usuarioRemoto: Usuario) =
        dao.insert(usuarioRemoto.toUsuarioMock())

    fun update(usuarioRemoto: Usuario) =
        dao.update(usuarioRemoto.toUsuarioMock())

    fun delete(login: String) = dao.delete(login)
}