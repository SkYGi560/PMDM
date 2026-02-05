package com.pmdm.navegacionlateral.ui.features

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmdm.navegacionlateral.models.Contacto


@Composable
fun ListadoScreen(
    modifier: Modifier = Modifier,
    listaContactos: List<Contacto>,
    onNavigateTo: (Int) -> Unit
) {
    Surface(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.95f)
            ) {
                this.items(count = listaContactos.size,
                    key = { listaContactos[it].id })
                {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(5.dp)
                            .clickable { onNavigateTo(listaContactos[it].id) },
                        shape = CardDefaults.elevatedShape
                    ) {
                        Text(
                            modifier = Modifier.fillMaxSize(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                            text = listaContactos[it].nombre,
                        )
                    }
                }
            }
        }

    }
}
