package com.pmdm.examennoviembre2025.ui.features.pregunta

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PreguntaScreen(
    preguntaUiState: PreguntaUiState,
    preguntaEvent: (PreguntaEvent) -> Unit
) {
    Box{
        CabezeraPregunta(
            imagen = preguntaUiState.imagenUrl,
            textoPregunta = preguntaUiState.pregunta
        )
        Respuesta(
            text = preguntaUiState.respuestas[0],
            isSelected = preguntaUiState.estaSeleccionada,
            onSelected = {preguntaEvent(PreguntaEvent.onSeleccionaRespuesta)}
        )
        Spacer(modifier = Modifier.height(12.dp))
        Respuesta(
            text = preguntaUiState.respuestas[1],
            isSelected = preguntaUiState.estaSeleccionada,
            onSelected = {preguntaEvent(PreguntaEvent.onSeleccionaRespuesta)}
        )
        Spacer(modifier = Modifier.height(12.dp))
        Respuesta(
            text = preguntaUiState.respuestas[2],
            isSelected = preguntaUiState.estaSeleccionada,
            onSelected = {preguntaEvent(PreguntaEvent.onSeleccionaRespuesta)}
        )
        Spacer(modifier = Modifier.height(12.dp))
        Respuesta(
            text = preguntaUiState.respuestas[3],
            isSelected = preguntaUiState.estaSeleccionada,
            onSelected = {preguntaEvent(PreguntaEvent.onSeleccionaRespuesta)}
        )
        Spacer(modifier = Modifier.height(12.dp))
        IconButton(
            onClick = {preguntaEvent(PreguntaEvent.onBotonAceptarClick)},
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Image(Icons.Filled.Favorite, contentDescription = "Imagen de check")
        }
        if(preguntaUiState.mostrarPuntos){
            AlertDialog(
                title = {
                    Column {
                        Icon(imageVector = Icons.Filled.Warning, contentDescription = "Aviso")
                        Text(
                            text = preguntaUiState.textoDialogo,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },
                onDismissRequest = { preguntaEvent(PreguntaEvent.onOculatDialogo) },
                confirmButton = {})
        }
    }
}

@Composable
fun CabezeraPregunta(
    imagen: String,
    textoPregunta: String
){
    Column {
        AsyncImage(
            model = imagen,
            contentDescription = "Imagen de la cabecera",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = textoPregunta)
        Spacer(modifier = Modifier.height(12.dp))
    }
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
