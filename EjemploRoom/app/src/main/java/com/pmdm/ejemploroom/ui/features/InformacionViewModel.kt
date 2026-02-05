package com.pmdm.ejemploroom.ui.features

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.ejemploroom.data.room.PedidoRepository
import com.pmdm.ejemploroom.data.room.ClienteRepository
import com.pmdm.ejemploroom.models.ClienteConPedido
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InformacionViewModel @Inject constructor(private val pedidoRepository: PedidoRepository,
                                               private val clienteRepository: ClienteRepository
):ViewModel() {


    var clienteConPedidoUiState by mutableStateOf(ClienteConPedidoUiState())


    init{
        viewModelScope.launch { clienteConPedidoUiState = clienteRepository.getPedidos().toClientesConPedidoUiSate()[0]
        }
    }


    fun List<ClienteConPedido>.toClientesConPedidoUiSate() = map{ it.toClienteConPedidoUiState()}
    fun ClienteConPedido.toClienteConPedidoUiState() = ClienteConPedidoUiState(cliente, pedidos)


}