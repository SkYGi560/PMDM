package com.pmdm.ejemploroom.data.room

import com.pmdm.ejemploroom.data.room.pedido.PedidoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PedidoRepository @Inject constructor(private val proveedorPedidos: PedidoDao) {

    suspend fun get () = withContext(Dispatchers.IO) {proveedorPedidos.get().toPedidos()}
}