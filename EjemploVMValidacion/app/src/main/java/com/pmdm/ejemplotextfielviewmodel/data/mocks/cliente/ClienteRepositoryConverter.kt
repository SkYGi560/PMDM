package com.pmdm.ejemplotextfielviewmodel.data.mocks.cliente

import com.pmdm.ejemplotextfielviewmodel.models.Cliente


fun Cliente.toClienteMock() = ClienteMock(id, dni, nombre, apellidos, telefono)
fun List<Cliente>.toClientesMock() = map {it.toClienteMock()}


fun ClienteMock.toCliente() = Cliente(id, dni, nombre, apellidos, telefono)
fun List<ClienteMock>.toClientes() = map {it.toCliente()}
