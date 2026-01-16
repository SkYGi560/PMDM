package com.pmdm.navegacion.ui.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.navegacion.ui.theme.EjemploNavegacionTheme

@Composable
fun PantallaScreen(
    pantalla: Int,
    pantallaDeDondeVengo: Int? = null,
    onNavigatePantallaAnterior: () -> Unit
) {
    val backgroundColor = when (pantalla) {
        1 -> MaterialTheme.colorScheme.primaryContainer
        2 -> MaterialTheme.colorScheme.secondaryContainer
        3 -> MaterialTheme.colorScheme.tertiaryContainer
        else -> throw IllegalArgumentException("Pantalla $pantalla no soportada")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = "Pantalla actual $pantalla",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )
        if (pantallaDeDondeVengo != null) {
            Text(
                modifier = Modifier.padding(bottom = 32.dp),
                text = "Vengo de pantalla $pantallaDeDondeVengo",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )
            Button(onClick = onNavigatePantallaAnterior) {
                Text(text = "Volver a pantalla $pantallaDeDondeVengo")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaScreenPreview() {
    EjemploNavegacionTheme {
        Surface {
            PantallaScreen(
                pantalla = 1,
                pantallaDeDondeVengo = 2,
                onNavigatePantallaAnterior = {})
        }
    }
}