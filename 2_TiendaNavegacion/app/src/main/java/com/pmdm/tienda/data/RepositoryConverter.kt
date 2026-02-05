package com.pmdm.tienda.data

import com.pmdm.tienda.data.mocks.articulo.ArticuloMock
import com.pmdm.tienda.data.mocks.cliente.ClienteMock
import com.pmdm.tienda.data.mocks.usuario.UsuarioMock
import com.pmdm.tienda.models.Articulo
import com.pmdm.tienda.models.Cliente
import com.pmdm.tienda.models.Direccion
import com.pmdm.tienda.ui.features.login.LoginUiState
import com.pmdm.tienda.models.Usuario


//region UsuarioMock
fun UsuarioMock.toUsuario(): Usuario = Usuario(this.login, this.password)
fun MutableList<UsuarioMock>.toUsuarios(): List<Usuario> =
    this.map { it.toUsuario() }
//endregion

//region Usuario
fun Usuario.toUsuarioMock(): UsuarioMock = UsuarioMock(this.login, this.password)
fun Usuario.toUsuarioUiState(logeado: Boolean) =
    LoginUiState(this.login, this.password, logeado)
//endregion

//region UsuarioUiState
fun LoginUiState.toUsuario() = Usuario(this.login, this.password)
//endregion


//region ArticuloMock
fun ArticuloMock.toArticulo(): Articulo =
    Articulo(this.id, this.url, this.precio, this.descripcion)

fun MutableList<ArticuloMock>.toArticulos(): List<Articulo> =
    this.map { it.toArticulo() }
//endregion

//region Articulo
fun Articulo.toArticuloMock(): ArticuloMock =
    ArticuloMock(this.id, this.url, this.precio, this.descripcion)

//endregion


//region ClienteMock
fun ClienteMock.toCliente(): Cliente = Cliente(
    this.dni,
    this.correo,
    this.nombre,
    this.telefono,
    Direccion(this.direccion?.calle, this.direccion?.ciudad, this.direccion?.codigoPostal),
    emptyList<Int>().toMutableList()
)


fun MutableList<ClienteMock>.toClientes(): List<Cliente> =
    this.map { it.toCliente() }
//endregion

//region Cliente
fun Cliente.toClienteMock(): ClienteMock =
    ClienteMock(
        this.nombre, this.correo, this.nombre, this.telefono,
        com.pmdm.tienda.data.mocks.cliente.Direccion(
            this.direccion?.calle,
            this.direccion?.ciudad,
            this.direccion?.codigoPostal,
        ),
        emptyList<Int>().toMutableList()
    )
//endregion
