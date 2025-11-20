package com.pmdm.ejemplotextfielviewmodel.data.mocks.cliente



class ClienteDaoMock {
    val clientesMock: MutableList<ClienteMock> = mutableListOf(
        ClienteMock(1, "12345678Z", "Ana", "García", "600 123 001"),
        ClienteMock(2, "23456789D", "Luis", "Pérez", "600 123 002"),
        ClienteMock(3, "34567890V", "María", "López", "600 123 003"),
        ClienteMock(4, "45678901G", "Juan", "Martínez", "600 123 004"),
        ClienteMock(5, "56789012R", "Sofía", "Rodríguez", "600 123 005")
    )

    fun getClientes(): List<ClienteMock> = clientesMock.toList()

    fun getCliente(dni: String): ClienteMock? = clientesMock.find { it.dni == dni }

    fun insert(cliente: ClienteMock): Boolean {
        if (clientesMock.any { it.dni == cliente.dni }) return false
        (1..clientesMock.size+1).forEach { i ->
            if (clientesMock.find { it.id == i } == null) {
                clientesMock.add(cliente.copy(id=i))
                return true
            }
        }
        return false
    }

    fun updateCliente(cliente: ClienteMock): Boolean {
        val index = clientesMock.indexOfFirst { it.dni == cliente.dni}
        if (index == -1) return false
        clientesMock[index] = cliente
        return true
    }

    fun  delete(dni:String): Boolean {
        val index = clientesMock.indexOfFirst { it.dni == dni }
        if (index == -1) return false
        clientesMock.removeAt(index)
        return true
    }


}
