package com.pmdm.ejemploroom.data.room

import com.pmdm.ejemploroom.data.room.cliente.ClienteDao
import com.pmdm.tienda.models.Cliente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClienteRepository @Inject constructor(private val proveedorClientes: ClienteDao) {

    suspend fun get(): List<Cliente> =
        withContext(Dispatchers.IO) { proveedorClientes.get().toClientes() }

    suspend fun getPedidos() = withContext(Dispatchers.IO) { proveedorClientes.getPedidos().toClientoConPedido() }


}