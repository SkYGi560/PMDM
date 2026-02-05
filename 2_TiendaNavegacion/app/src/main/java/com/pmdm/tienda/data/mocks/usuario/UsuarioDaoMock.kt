package com.pmdm.tienda.data.mocks.usuario

import javax.inject.Inject


class UsuarioDaoMock{
    private var usuarios = mutableListOf<UsuarioMock>(
        UsuarioMock("juanjo@gmail.com", "juanjo1234"),
        UsuarioMock("pepe@gmail.com", "pepe1234"),
        UsuarioMock("xusa@gmail.com", "xusa1234")
    )

    fun get(): MutableList<UsuarioMock> = usuarios
    fun get(login: String): UsuarioMock? = usuarios.find { u -> u.login == login }
    fun insert(usuarioRemoto: UsuarioMock) = usuarios.add(usuarioRemoto)
    fun update(usuarioRemoto: UsuarioMock) {
        val posicion = usuarios.indexOf(get(usuarioRemoto.login))
        if (posicion != -1) usuarios.removeAt(posicion)
        usuarios.add(usuarioRemoto)
    }

}
