package com.pmdm.tienda.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.pmdm.tienda.data.mocks.pedido.ArticuloDePedidoMock
import com.pmdm.tienda.data.mocks.pedido.PedidoDaoMock
import com.pmdm.tienda.data.mocks.pedido.PedidoMock
import com.pmdm.tienda.models.ArticuloDePedido
import com.pmdm.tienda.models.Pedido
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class PedidoRepository @Inject constructor(private val proveedorPedido: PedidoDaoMock) {



    fun get(): List<Pedido> = proveedorPedido.getPedidos().toPedidos()
    fun get(dniCliente:String): List<Pedido> = proveedorPedido.getPedidos(dniCliente).toPedidos()
    fun get(id: Long): Pedido? = proveedorPedido.getPedido(id)?.toPedido()
    fun insert(pedido: Pedido) = proveedorPedido.insert(
        pedido.toPedidoMock(),
        pedido.articulos.toList().toArticulosConPedidoMock(pedido.pedidoId)
    )

    fun numeroArticulos(id: Long) = proveedorPedido.getCantidadArticulosEnPedido(id)

    fun generarIdPedido()=(proveedorPedido.getPedidos().size+1).toLong()
    private fun PedidoMock.toPedido(): Pedido {
        val articulos = proveedorPedido.getArticulosPedido(this.pedidoId)
        return Pedido(
            this.pedidoId,
            this.dniCliente,
            this.total,
            this.fecha,
            articulos.toArticuloDePedido(this.pedidoId).toMutableList()
        )
    }


    private fun List<PedidoMock>.toPedidos(): List<Pedido> =
        this.map { it.toPedido() }

    private fun Pedido.toPedidoMock(): PedidoMock =
        PedidoMock(this.pedidoId, this.dniCliente, this.total, this.fecha)

    private fun ArticuloDePedido.toArticuloConPedidoMock(idPedido: Long) =
        ArticuloDePedidoMock(this.articuloId, idPedido, this.tamaño, this.cantidad)

    private fun List<ArticuloDePedido>.toArticulosConPedidoMock(idPedido: Long) =
        this.map { it.toArticuloConPedidoMock(idPedido) }

    private fun ArticuloDePedidoMock.toArticuloDePedido() =
        ArticuloDePedido(this.articuloId, this.tamaño, this.cantidad)

    private fun List<ArticuloDePedidoMock>.toArticuloDePedido(idPedido: Long) =
        this.map { it.toArticuloDePedido() }


}