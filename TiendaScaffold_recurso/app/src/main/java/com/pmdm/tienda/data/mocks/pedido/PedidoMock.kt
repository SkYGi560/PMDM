package com.pmdm.tienda.data.mocks.pedido

import androidx.annotation.NonNull


//CLAVE AJENA SOBRE EL CAMPO DNICLIENTE AL CAMPO DNI DE LA TABLA CLIENTE

data class PedidoMock(
    val pedidoId: Long,
    val dniCliente: String,
    val total: Float,
    val fecha: Long
)
