package com.pmdm.tienda.data

import com.pmdm.tienda.data.mocks.usuario.UsuarioDaoMock
import com.pmdm.tienda.data.mocks.usuario.UsuarioMock
import com.pmdm.tienda.models.Usuario
import javax.inject.Inject


class UsuarioRepository @Inject constructor(
    private val proveedorUsuarios: UsuarioDaoMock
) {
    fun get(): List<Usuario> = proveedorUsuarios.get().toUsuarios()
    fun get(login: String): Usuario? = proveedorUsuarios.get(login)?.toUsuario()
    fun insert(usuario: Usuario) = proveedorUsuarios.insert(usuario.toUsuarioMock())
    fun update(usuario: Usuario)=proveedorUsuarios.update(usuario.toUsuarioMock())

    private fun UsuarioMock.toUsuario(): Usuario = Usuario(this.login, this.password)
    private fun MutableList<UsuarioMock>.toUsuarios(): List<Usuario> =
        this.map { it.toUsuario() }
    private fun Usuario.toUsuarioMock(): UsuarioMock = UsuarioMock(this.login, this.password)
}