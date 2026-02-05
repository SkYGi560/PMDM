package com.pmdm.ejemploroom.ui.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun InformacionScreen(
    modifier: Modifier = Modifier,
    clientesConPedidoUiState: ClienteConPedidoUiState
) {
    Column(modifier = modifier.then(Modifier.padding(10.dp))) {
        Text(modifier = Modifier.align(Alignment.CenterHorizontally),text = clientesConPedidoUiState.cliente.nombre, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.size(10.dp))
        LazyColumn {

            items(items = clientesConPedidoUiState.pedidos, key = { it.id })
            {
                ElevatedCard(modifier=Modifier.padding(2.dp),shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp)) {
                    Text(modifier=Modifier.padding(12.dp),text = "$it")
                }

            }
        }
    }

}