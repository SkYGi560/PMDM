package com.pmdm.tienda.data

import com.pmdm.tienda.data.mocks.cliente.ClienteDaoMock
import com.pmdm.tienda.models.Cliente
import javax.inject.Inject


class ClienteRepository @Inject constructor(private val proveedorClientes: ClienteDaoMock) {


    fun get(): List<Cliente> = proveedorClientes.get().toClientes()
    fun get(login: String): Cliente = proveedorClientes.get(login)!!.toCliente()
    fun insert(cliente: Cliente) = proveedorClientes.insert(cliente.toClienteMock())

    fun añadirAFavoritos(correo:String, idArticulo:Int)=proveedorClientes.añadirAFavoritos(correo,idArticulo)

}