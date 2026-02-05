package com.pmdm.tienda.data.mocks.pedido


//ENTIDAD PARA CREAR LA RELACIÓN MUCHOS A MUCHOS ENTRE ARTICULOS Y PEDIDOS
data class ArticuloDePedidoMock (
    val articuloId: Int,
    val pedidoId: Long,
    val tamaño: Short,
    val cantidad:Int)