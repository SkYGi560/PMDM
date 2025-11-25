package com.pmdm.examennoviembre2025.ui.features.pregunta

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pmdm.examennoviembre2025.models.Pregunta
import com.pmdm.examennoviembre2025.ui.features.JuegoUiState
import com.pmdm.examennoviembre2025.ui.theme.ExamenNoviembre2025Theme

@Composable
fun Pregunta(
    modifier: Modifier = Modifier,
    juegoUiState: JuegoUiState,
    preguntaEvent: (PreguntaEvent) -> Unit
) {

    // estado que solo nos sirve para gestionar el click de los check,
    var preguntaSeleccionada by remember { mutableStateOf<Int?>(null) }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.then(
            modifier
                .padding(10.dp)
                .fillMaxSize()
        )
    )
    {
        CabeceraPregunta(juegoUiState)
        Spacer(modifier = Modifier.size(2.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            juegoUiState.cuestion.respuestas.forEachIndexed { index, respuesta ->
                Respuesta(
                    text = respuesta,
                    isSelected = preguntaSeleccionada == index,
                    onSelected = {
                        preguntaSeleccionada = index
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        IconButton(
            onClick = {
                preguntaEvent(PreguntaEvent.OnOpcionSeleccionada(preguntaSeleccionada ?: -1))
                preguntaEvent(PreguntaEvent.OnSiguiente)
                preguntaSeleccionada = null
            },
            modifier = Modifier
                .size(56.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
                .align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Siguiente pregunta",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        if (juegoUiState.muestraDialogoPuntos) {
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
                            text = "${juegoUiState.jugador} has ganado ${juegoUiState.puntos}",
                            textAlign = TextAlign.Center
                        )
                    }
                },
                onDismissRequest = {
                    preguntaEvent(PreguntaEvent.OnCambiaEstadoDialogoPuntos)
                    preguntaEvent(PreguntaEvent.OnVolver)
                },
                confirmButton = {})

        }

    }
}

@Composable
fun CabeceraPregunta(
    preguntaUiState: JuegoUiState
) {

    AsyncImage(
        model = preguntaUiState.cuestion.imagenUrl,
        contentDescription = "Imagen de la pregunta",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(400.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))

    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = preguntaUiState.cuestion.pregunta,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 24.dp),
    )


}

@Composable
fun Respuesta(
    text: String,
    isSelected: Boolean,
    onSelected: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        verticalAlignment = CenterVertically
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = onSelected
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable

fun PreguntasPreview() {
    ExamenNoviembre2025Theme {
        var juego by remember { mutableStateOf(JuegoUiState()) }

        juego = JuegoUiState(
            cuestion = Pregunta(
                "¿Qué planeta es conocido como el \"planeta rojo\"?",
                respuestas = listOf("Venus", "Júpiter", "Marte", "Saturno"),
                opcionCorrecta = 2,
                imagenUrl = "http://10.0.2.2/imagenes_juego/CN_Imagen1.jpg"
            ),
            jugador = "Juanma",
            puntos = 0,
            nivel = 10
        )
        Pregunta(juegoUiState = juego) { }
    }
}