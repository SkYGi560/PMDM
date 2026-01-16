package com.pmdm.tienda.ui.features.tienda


data class ArticuloDePedidoUiState(
    val articuloId: Int,
    val url:String,
    val descripcion:String,
    val tamaño: Short,
    val precio: Float,
    val cantidad: Int
)

data class PedidoUiState(
    val pedidoId: Long,
    val dniCliente: String,
    val total: Float,
    val fecha: Long,
    val articulos: List<ArticuloDePedidoUiState>,
    val iniciado: Boolean
)

