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
import com.pmdm.examennoviembre2025.models.Pregunta
import com.pmdm.examennoviembre2025.ui.features.JuegoUiState
import kotlin.collections.mutableListOf
import kotlin.enums.enumEntries

@Composable
fun Inicio(
    juegoUiState: JuegoUiState,
    validacion: Validacion,
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
        Spacer(Modifier.height(20.dp))
        FilledTonalButton({

            onInicioEvent(InicioEvent.OnIniciaJuego)

        }) { Text("Jugar") }

        if (juegoUiState.muestraDialogoAviso) {
            AlertDialog(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            modifier = Modifier.size(50.dp),
                            imageVector = Icons.Filled.Warning,
                            contentDescription = "Aviso",
                            tint = MaterialTheme.colorScheme.inversePrimary
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = juegoUiState.textoDialogoAviso,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                onDismissRequest = {},
                confirmButton = {
                    TextButton({ onInicioEvent(InicioEvent.OnCambiaEstadoDialogoAviso) }) {
                        Text(text = "Entendido")
                    }
                })
        }
    }
}

@Composable
fun Categorias(juegoUiState: JuegoUiState, onInicioEvent: (InicioEvent) -> Unit) {
    val temas = remember {  listOf("Ciencia", "Arte", "Geografía", "Historia", "Entretenimiento")}

    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0..temas.size - 1)
            FilterChip(
                selected = juegoUiState.temas.contains(temas[i]),
                onClick = {
                    onInicioEvent(InicioEvent.OnSeleccionaTemas(temas[i]))
                },
                label = { Text(text = temas[i]) },
                modifier = Modifier.padding(end = 8.dp)
            )
    }
}

@Composable
fun DatosJugador(
    juegoUiState: JuegoUiState,
    validacion: Validacion,
    inicioEvent: (InicioEvent) -> Unit
) {

    OutlinedTextFieldName(
        modifier = Modifier.focusable(false),
        nameState = juegoUiState.jugador,
        onValueChange = { inicioEvent(InicioEvent.OnCambiaNombre(nombre = it)) },
        validacionState = validacion
    )
    Spacer(Modifier.height(20.dp))
    Column(verticalArrangement = Arrangement.Center)
    {
        Text(
            text = "Selecciona el nivel",
            style = MaterialTheme.typography.titleMedium,
        )
        Slider(
            value = juegoUiState.nivel.toFloat(),
            valueRange = 10f..50f,
            steps = 3,
            onValueChange = {
                inicioEvent(InicioEvent.OnSeleccionaNivel(it.toInt()))
            })
        Text(text = "${juegoUiState.nivel} preguntas")
    }
}


@Composable
@Preview(showBackground = true)
fun InicioPreview() {
    var juego by remember {
        mutableStateOf(
            JuegoUiState(
                cuestion = Pregunta(
                    "¿Qué planeta es conocido como el \"planeta rojo\"?",
                    respuestas = listOf("Venus", "Júpiter", "Marte", "Saturno"),
                    opcionCorrecta = 2,
                    imagenUrl = "http://10.0.2.2/imagenes_juego/CN_Imagen1.jpg"
                ),
                jugador = "",
                puntos = 0,
                nivel = 10,
                temas = listOf("Ciencia")
            )
        )
    }
    var validacion: Validacion by remember {  mutableStateOf(object : Validacion{} as Validacion)}
    Inicio(juegoUiState = juego, validacion = validacion, onInicioEvent = {
        when (it) {
            is InicioEvent.OnCambiaNombre -> {
                juego = juego.copy(jugador = it.nombre)
                validacion = object  : Validacion{
                    override val hayError: Boolean
                        get() = it.nombre.isEmpty()
                    override val mensajeError: String?
                        get() = "El nombre no puede estar vacio"
                }
            }

            is InicioEvent.OnIniciaJuego -> {}
            is InicioEvent.OnSeleccionaTemas ->
                if (juego.temas.contains(it.tema)) juego =
                    juego.copy(temas = quitaTemaDeLista(juego.temas, it.tema))
                else juego.copy(temas = añadeTemaALista(juego.temas, it.tema))

            is InicioEvent.OnSeleccionaNivel -> juego = juego.copy(nivel = it.nivel)
            is InicioEvent.OnCambiaEstadoDialogoAviso -> {}
        }

    })

}

private fun quitaTemaDeLista(temas: List<String>, tema: String): List<String> {
    var listaNueva = mutableListOf<String>()
    temas.forEach { if (it != tema) listaNueva.add(it) }
    return listaNueva
}

private fun añadeTemaALista(temas: List<String>, tema: String): List<String> {
    var listaNueva = temas.toMutableList()
    listaNueva.add(tema)
    return listaNueva
}
