package com.pmdm.ejemploroom.data.room.cliente

import androidx.room.Embedded
import androidx.room.Relation
import com.pmdm.ejemploroom.data.room.pedido.PedidoEntity

data class ClienteConPedidosEntity(
    // Sabe recuperar el objeto embebido.
    @Embedded val cliente: ClienteEntity,
    @Relation(
        parentColumn = "dni",
        // Nombre de la columna en la parte del muchos
        entityColumn = "dni_cliente"
    )
    val pedidos: List<PedidoEntity>
)