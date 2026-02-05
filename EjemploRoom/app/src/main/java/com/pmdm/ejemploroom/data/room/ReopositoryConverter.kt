package com.pmdm.ejemploroom.data.room


import com.pmdm.ejemploroom.data.room.cliente.ClienteConPedidosEntity
import com.pmdm.ejemploroom.data.room.cliente.ClienteEntity
import com.pmdm.ejemploroom.data.room.pedido.PedidoEntity
import com.pmdm.ejemploroom.models.ClienteConPedido

import com.pmdm.tienda.models.Cliente
import com.pmdm.tienda.models.Direccion
import com.pmdm.tienda.models.Pedido


fun List<ClienteEntity>.toClientes(): List<Cliente> =
    this.map { it.toCliente() }

fun ClienteEntity.toCliente(): Cliente = Cliente(
    this.dni,
    this.nombre,
    this.apellidos,
    Direccion(this.direccion?.calle, this.direccion?.ciudad, this.direccion?.pais, this.direccion?.codigoPostal),
)

fun List<PedidoEntity>.toPedidos():List<Pedido> =
    this.map { it.toPedido() }

fun PedidoEntity.toPedido(): Pedido =Pedido (this.id, this.dniCliente,this.fecha )

fun List<ClienteConPedidosEntity>.toClientoConPedido (): List<ClienteConPedido> =
    this.map { it.toClienteConPedido() }

fun ClienteConPedidosEntity.toClienteConPedido()=ClienteConPedido(this.cliente.toCliente(), this.pedidos.toPedidos().toMutableList())

