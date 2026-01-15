package com.pmdm.menulateral.ui.features.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListadoScreen(
    paddingValues: PaddingValues
){
    Surface(modifier = Modifier.padding(paddingValues)) {
        LazyColumn {
            items(20) { i ->
                ElevatedCard(
                    Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                ) {
                    Text(
                        text = "Contacto ${i + 1}",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}