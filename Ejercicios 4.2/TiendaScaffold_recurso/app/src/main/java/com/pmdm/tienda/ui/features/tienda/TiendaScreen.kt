package com.pmdm.tienda.ui.features.tienda

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pmdm.tienda.models.Cliente
import com.pmdm.tienda.ui.features.tienda.components.Carrito
import com.pmdm.tienda.ui.features.tienda.components.Escaparate
import com.pmdm.tienda.ui.features.tienda.components.NavigationBarTienda
import com.pmdm.tienda.ui.features.tienda.components.TopAppBarTienda


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TiendaScreen(
    clienteUiState: Cliente,
    articulos: List<ArticuloUiState>,
    articuloSeleccionado: ArticuloUiState?,
    filtro: String,
    estaFiltrando: Boolean,
    carrito: Boolean,
    numerArticulos: Int,
    totalCompra: Float,
    pedido: PedidoUiState,
    tallaUiState: TallaUiState,
    onTallaEvent: (TallaEvent) -> Unit,
    onTiendaEvent: (TiendaEvent) -> Unit,
) {

    Scaffold(
        topBar = { TopAppBarTienda(
            estaFiltrando = estaFiltrando,
            onTiendaEvent = onTiendaEvent
        )},
        bottomBar = { NavigationBarTienda() }
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()

        )
        {
            if (!carrito) {
                Escaparate(
                    articulos = articulos,
                    articuloSeleccionado = articuloSeleccionado,
                    tallaUiState = tallaUiState,
                    onTallaEvent = onTallaEvent,
                    onTiendaEvent = onTiendaEvent
                )
            } else Carrito(pedido = pedido, onTiendaEvent = onTiendaEvent)
        }
    }
}