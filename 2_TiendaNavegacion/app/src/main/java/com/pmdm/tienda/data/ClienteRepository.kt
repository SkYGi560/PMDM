package com.pmdm.tienda.data

import com.pmdm.tienda.data.mocks.cliente.ClienteDaoMock
import com.pmdm.tienda.data.mocks.cliente.ClienteMock
import com.pmdm.tienda.models.Cliente
import com.pmdm.tienda.models.Direccion
import javax.inject.Inject


class ClienteRepository @Inject constructor(private val proveedorClientes: ClienteDaoMock) {

    fun get(): List<Cliente> = proveedorClientes.get().toClientes()
    fun get(login: String): Cliente = proveedorClientes.get(login)!!.toCliente()
    fun insert(cliente: Cliente) = proveedorClientes.insert(cliente.toClienteMock())
    fun update(cliente:Cliente)=proveedorClientes.updateCliente(cliente.toClienteMock())

    fun añadeAFavoritos(correo:String, idArticulo:Int)=proveedorClientes.añadeAFavoritos(correo,idArticulo)

    private fun ClienteMock.toCliente(): Cliente = Cliente(
        this.dni,
        this.correo,
        this.nombre,
        this.telefono,
        Direccion(this.direccion?.calle, this.direccion?.ciudad, this.direccion?.codigoPostal),
        this.favoritos
    )


    private fun MutableList<ClienteMock>.toClientes(): List<Cliente> =
        this.map { it.toCliente() }

    private fun Cliente.toClienteMock(): ClienteMock =
        ClienteMock(
            this.dni, this.correo, this.nombre, this.telefono,
            com.pmdm.tienda.data.mocks.cliente.Direccion(
                this.direccion?.calle,
                this.direccion?.ciudad,
                this.direccion?.codigoPostal,
            ),
            this.favoritos
        )

}