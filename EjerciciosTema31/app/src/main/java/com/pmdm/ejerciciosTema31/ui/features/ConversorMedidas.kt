package com.pmdm.ejerciciosTema31.ui.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.ejerciciosTema31.ui.theme.EjerciciosTema31Theme

@Composable
fun BaseMedidas(
    textoEntrada: String,
    entrada: String,
    textoSalida: String,
    salida: String,
    onEntradaChanged: (String) -> Unit,
) {
    Row(
        modifier = Modifier.padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    )
    {
        Text(text = textoEntrada)
        TextField(
            modifier = Modifier
                .width(120.dp)
                .padding(5.dp),
            value = entrada,
            onValueChange = onEntradaChanged
        )

        TextField(
            modifier = Modifier
                .width(120.dp)
                .padding(5.dp),
            value = salida,
            onValueChange = {},
            enabled = false
        )
        Text(text = textoSalida)
    }
}

@Preview(showBackground = true)
@Composable
fun ConversorMedidas() {
    var numeroEntrada by rememberSaveable { mutableDoubleStateOf(0.0) }
    var numeroSalida by rememberSaveable { mutableDoubleStateOf(0.0) }
    val onEntradaChanged = {numeroEntrada.toString() ->
        val km = numeroEntrada.toDouble()
        numeroSalida = numeroEntrada*0.621371}
    EjerciciosTema31Theme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BaseMedidas(
                "Kilometros",
                numeroEntrada.toString(),
                "Millas",
                numeroSalida.toString(),
                onEntradaChanged = onEntradaChanged

            )
        }
    }
}