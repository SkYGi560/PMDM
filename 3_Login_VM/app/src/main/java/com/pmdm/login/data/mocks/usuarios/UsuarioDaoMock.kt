package org.ejercicio5.data.mocks.usuarios

class UsuarioDaoMock {

    private var usuarios : MutableList<UsuarioMock> = mutableListOf(
        UsuarioMock(
            login = "Juan",
            password = "Juan2025__"
        ),
        UsuarioMock(
            login = "Maria",
            password = "5678"
        ),
        UsuarioMock(
            login = "Pedro",
            password = "abcd"
        )
    )

    fun get(): MutableList<UsuarioMock> = usuarios

    fun get(login: String): UsuarioMock? =
        usuarios.find { u -> u.login == login }

    fun insert(usuarioRemoto: UsuarioMock) = usuarios.add(usuarioRemoto)

    fun update(usuarioRemoto: UsuarioMock) =
        usuarios.find { u -> u.login == usuarioRemoto.login }?.let {
            u -> usuarios[usuarios.indexOf(u)] = usuarioRemoto.copy()
        }

    fun delete(login: String) = usuarios.removeIf {
        u -> u.login == login
    }

}