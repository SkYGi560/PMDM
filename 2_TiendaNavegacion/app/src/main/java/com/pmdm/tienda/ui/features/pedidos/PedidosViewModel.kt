package com.pmdm.tienda.ui.features.pedidos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.pmdm.tienda.data.PedidoRepository
import com.pmdm.tienda.data.mocks.ArticuloRepository
import com.pmdm.tienda.models.ArticuloDePedido
import com.pmdm.tienda.models.Pedido
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class PedidosViewModel @Inject constructor(
    private val pedidoRepository: PedidoRepository,
    private val articuloRepository: ArticuloRepository
) : ViewModel() {

    var dni: String? = null
    var pedidosUiState by mutableStateOf(getPedidos(dni))
    var muestraPedidos by mutableStateOf(true)
    var pedidoSeleccionadoUiState: PedidoConArticuloUiState? by mutableStateOf(null)
    val pedidoSeleccionadoEvent: (PedidoConArticuloUiState) -> Unit by mutableStateOf({
        pedidoSeleccionadoUiState = it
        muestraPedidos = false
    })
    val muestraPedidoEvent: (Boolean) -> Unit by mutableStateOf({
        muestraPedidos = it
    })


    @RequiresApi(Build.VERSION_CODES.O)
    fun getPedidos(dni: String?): List<PedidoConArticuloUiState> {
        if (dni != null) return pedidoRepository.get(dni).toPedidosConArticuloUiState()
        else return listOf(PedidoConArticuloUiState(listOf(),-1,0F,0L))
    }


    private fun List<Pedido>.toPedidosConArticuloUiState(): List<PedidoConArticuloUiState> {
        return this.map { it.toPedidoConArticuloUiState() }
    }

    private fun Pedido.toPedidoConArticuloUiState() = PedidoConArticuloUiState(
        articulos = this.articulos.toArticulosConPedido(),
        pedidoId = this.pedidoId,
        total = this.total, this.fecha
    )

    private fun ArticuloDePedido.toArticuloConPedido(): ArticuloConPedido {
        val articulo = articuloRepository.get(this.articuloId)
        val url = articulo?.url ?: "@drawable/imagen0"
        return ArticuloConPedido(
            url = url,
            cantidad = this.cantidad,
            precio = articulo?.precio ?: 0f,
            tamaño = this.tamaño
        )
    }

    private fun MutableList<ArticuloDePedido>.toArticulosConPedido(): List<ArticuloConPedido> {
        return this.map { it.toArticuloConPedido() }
    }

    fun actualizaPedido(dni: String) {
        pedidosUiState = pedidoRepository.get(dni).toPedidosConArticuloUiState()
    }


}