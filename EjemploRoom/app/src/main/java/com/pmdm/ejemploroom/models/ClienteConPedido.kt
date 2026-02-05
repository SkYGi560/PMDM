package com.pmdm.ejemploroom.models

import com.pmdm.tienda.models.Cliente
import com.pmdm.tienda.models.Pedido

data class ClienteConPedido(val cliente: Cliente=Cliente(),
                            val pedidos:MutableList<Pedido> = mutableListOf())


