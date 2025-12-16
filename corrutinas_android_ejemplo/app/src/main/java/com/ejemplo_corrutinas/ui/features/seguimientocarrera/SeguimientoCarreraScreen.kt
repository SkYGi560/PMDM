package com.ejemplo_corrutinas.ui.features.seguimientocarrera

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ejemplo_corrutinas.R
import com.ejemplo_corrutinas.ui.theme.EjemploCorrutinasTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@Composable
fun SeguimientoCarreraScreen(
    corredor1: CorredorUiState,
    corredor2: CorredorUiState,
    enCarrera: Boolean,
    onSeguimientoCarreraEvent: (SeguimientoCarreraEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medio)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.carrera_procesos),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(R.drawable.run_circle_24px),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.tamano_icono))
                    .padding(bottom = dimensionResource(R.dimen.padding_medio)),
                tint = MaterialTheme.colorScheme.primary
            )
            EstadoCorredor(
                nombre = corredor1.nombre,
                procentajeProgreso = corredor1.porcentajeProgreso
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_grande)))
            EstadoCorredor(
                nombre = corredor2.nombre,
                procentajeProgreso = corredor2.porcentajeProgreso
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_grande)))
            ControlesCarrera(
                enCarrera = enCarrera,
                onEmpezarPararClick = { onSeguimientoCarreraEvent(SeguimientoCarreraEvent.OnEmpezarPararClick) },
                onReiniciar = { onSeguimientoCarreraEvent(SeguimientoCarreraEvent.OnReiniciarClick) }
            )
        }
    }
}

@Composable
private fun EstadoCorredor(
    nombre: String,
    procentajeProgreso: Int,
    modifier: Modifier = Modifier
) {
    Row {
        Text(
            text = nombre,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_pequeño))
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_pequeño))
        ) {
            LinearProgressIndicator(
                progress = procentajeProgreso / 100f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.altura_indicador_progreso))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.radio_esquina_indicador_progreso)))
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$procentajeProgreso %",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "100 %",
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
private fun ControlesCarrera(
    onEmpezarPararClick: (Boolean) -> Unit,
    onReiniciar: () -> Unit,
    modifier: Modifier = Modifier,
    enCarrera: Boolean = true,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { onEmpezarPararClick(!enCarrera) }) {
            Text(if (enCarrera) stringResource(R.string.pausar) else stringResource(R.string.empezar))
        }

        Button(
            enabled = !enCarrera,
            onClick = onReiniciar) {
            Text(stringResource(R.string.reiniciar))
        }
    }
}


@Preview
@Composable
private fun SeguimientoCarreraScreenPreview() {
    // Creamos los estados a usar en la UI
    var corredor1 by remember {
        mutableStateOf(CorredorUiState(nombre = "Corredor 1"))
    }
    var corredor2 by remember {
        mutableStateOf(CorredorUiState(nombre = "Corredor 2"))
    }
    var enCarrera by remember { mutableStateOf(false) }

    // Cada vez que ha un cambio en uno de estos estados se recompone la UI
    // y se lanza el LaunchedEffect con las corrutinas de simulación de la carrera
    LaunchedEffect(
        key1 = enCarrera,
        key2 = corredor1.porcentajeProgreso,
        key3 = corredor2.porcentajeProgreso
    ) {
        // Cada corrutina hara que avance el corredor cambiando su estado
        // y por tanto recomponinedo la UI y relanzando el LaunchedEffect
        // Ambos procesos se ejecutan en un contexto de corrutina diferente
        // con Dispatchers.Default para que el sistema decida el hilo más adecuado para el preview
        val jobCorredor1 = launch(Dispatchers.Default) {
            if (enCarrera && corredor1.porcentajeProgreso < 100)
                corredor1 = corredor1.avanza()
        }
        val jobCorredor2 = launch(Dispatchers.Default) {
            if (enCarrera && corredor2.porcentajeProgreso < 100)
                corredor2 = corredor2.avanza()
        }
        // Esperamos a que los dos correodres avancen si lo tienen que hacer
        joinAll(jobCorredor1, jobCorredor2)
        // Si ambos corredores han llegado al 100% paramos la carrera
        if (corredor1.porcentajeProgreso == 100 && corredor2.porcentajeProgreso == 100)
            enCarrera = false
    }

    EjemploCorrutinasTheme {
        Surface {
            SeguimientoCarreraScreen(
                corredor1 = corredor1,
                corredor2 = corredor2,
                enCarrera = enCarrera,
                onSeguimientoCarreraEvent = {
                    when (it) {
                        SeguimientoCarreraEvent.OnEmpezarPararClick -> {
                            enCarrera = !enCarrera
                        }

                        SeguimientoCarreraEvent.OnReiniciarClick -> {
                            corredor1 = corredor1.reinicia()
                            corredor2 = corredor2.reinicia()
                        }
                    }
                }
            )
        }
    }
}

