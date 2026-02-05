package com.pmdm.ejemploroom.ui.features

import com.pmdm.tienda.models.Cliente
import com.pmdm.tienda.models.Pedido

data class ClienteConPedidoUiState(val cliente: Cliente=Cliente(),
                                   val pedidos:MutableList<Pedido> = mutableListOf())


