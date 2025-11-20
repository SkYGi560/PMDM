package com.pmdm.ejemplotextfielviewmodel.data.mocks.cliente

import com.pmdm.ejemplotextfielviewmodel.models.Cliente


class ClienteRepository {

    var clienteDaoMock = ClienteDaoMock()

    fun getClientes(): List<Cliente> = clienteDaoMock.getClientes().toClientes()

    fun getCliente(dni: String): Cliente? = clienteDaoMock.getCliente(dni = dni)?.toCliente()

    fun updateCliente(cliente: Cliente): Boolean = clienteDaoMock.updateCliente(cliente.toClienteMock())
    fun  delete(dni:String): Boolean =  clienteDaoMock.delete(dni = dni)
    fun insert(cliente: Cliente): Boolean = clienteDaoMock.insert(cliente = cliente.toClienteMock())


}