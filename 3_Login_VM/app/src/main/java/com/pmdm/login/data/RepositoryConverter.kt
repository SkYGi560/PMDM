package org.ejercicio5.data

import org.ejercicio5.data.mocks.usuarios.UsuarioMock
import org.ejercicio5.models.Usuario

fun UsuarioMock.toUsuario() = Usuario(
    login = this.login,
    password = this.password
)

fun Usuario.toUsuarioMock() = UsuarioMock(
    login = this.login,
    password = this.password
)