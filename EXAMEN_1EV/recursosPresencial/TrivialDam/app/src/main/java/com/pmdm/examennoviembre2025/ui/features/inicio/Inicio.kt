package com.pmdm.examennoviembre2025.ui.features.inicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import com.pmdm.examennoviembre2025.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldName
import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.pmdm.examennoviembre2025.ui.features.JuegoUiState

@Composable
fun Inicio(
    modifier: Modifier,
    juegoUiState: JuegoUiState,
    validacion: ValidacionInicioUiState,
    onInicioEvent: (InicioEvent) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "JUEGO TRIVIALDAM",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Selecciona las categorías",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Categorias(juegoUiState, onInicioEvent)
        Spacer(Modifier.height(40.dp))
        Image(
            modifier = Modifier.size(280.dp),
            painter = painterResource(R.drawable.trivial),
            contentDescription = "Trivial"
        )
        Spacer(Modifier.height(40.dp))
        Spacer(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.inversePrimary)
                .height(2.dp)
                .fillMaxWidth()
        )
        Spacer(Modifier.height(40.dp))
        DatosJugador(juegoUiState, validacion, onInicioEvent)

    }
}

@Composable
fun Categorias(juegoUiState: JuegoUiState, onInicioEvent: (InicioEvent) -> Unit) {
    val temas = remember { listOf("Ciencia", "Arte", "Geografía", "Historia", "Entretenimiento") }

    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0..temas.size - 1)
            FilterChip(
                selected = juegoUiState.inicioUiState.estaCategoriaSeleccionada,
                onClick = {
                    onInicioEvent(InicioEvent.onCategoriaClick(i))
                },
                label = { Text(text = temas[i]) },
                modifier = Modifier.padding(end = 8.dp)
            )
    }
}

@Composable
fun DatosJugador(
    juegoUiState: JuegoUiState,
    validacion: ValidacionInicioUiState,
    inicioEvent: (InicioEvent) -> Unit
) {
    OutlinedTextFieldName(
        nameState = juegoUiState.inicioUiState.jugadorNombre,
        validacionState = validacion.validacionNombre,
        onValueChange = {inicioEvent(InicioEvent.onCambiaNombre(it))}
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Selecciona el nivel")
    Slider(
        value = juegoUiState.inicioUiState.numeroPreguntas,
        onValueChange = {inicioEvent(InicioEvent.onCambiaSlider(it))},
        steps = 3,
        valueRange = 10f..50f
    )
    Text(text = "${juegoUiState.inicioUiState.numeroPreguntas} preguntas")
    Spacer(modifier = Modifier.height(8.dp))
    FilledTonalButton(
        onClick = {inicioEvent(InicioEvent.onJugarClick)}
    ){

    }


}


@Composable
@Preview(showBackground = true)
fun InicioPreview() {


}


