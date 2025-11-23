package com.pmdm.eventos.ui.features.presentacion_eventos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog

import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldEmail
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldName
import com.pmdm.eventos.R
import com.pmdm.eventos.ui.theme.PresentacionEventosTheme

@Composable
fun PresentacionEventoScreen(
    modifier: Modifier = Modifier,
    dialogoUiState: DialogoUiState,
    onDialogoEvent: (DialogoEvent) -> Unit,
    eventosUiState: EventoUiState,
    onEventosEvent: (EventoEvent) -> Unit
) {
    Column(
        modifier = modifier.then(
            Modifier
                .padding(8.dp)
                .fillMaxSize()
        )
    ) {

        CabeceraMenu()
        ImagenEvento(eventosUiState = eventosUiState, eventosEvent = onEventosEvent)
        LineaSeguidores(eventosUiState, onEventosEvent)
        CuerpoInformacion(
            eventoUiState = eventosUiState,
            onEventoEvent = onEventosEvent,
            dialogoUiState = dialogoUiState,
            onDialogoEvent = onDialogoEvent
        )

    }
}

@Composable
fun CuerpoInformacion(
    eventoUiState: EventoUiState,
    onEventoEvent: (EventoEvent) -> Unit,
    dialogoUiState: DialogoUiState,
    onDialogoEvent: (DialogoEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = eventoUiState.titulo, style = TextStyle(
                fontSize = TextUnit(value = 20f, type = TextUnitType.Sp),
                letterSpacing = TextUnit(value = 4f, type = TextUnitType.Sp),
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            ), color = Color(0xAA880000)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                modifier = Modifier.weight(0.8f),
                text = eventoUiState.realizado, style = TextStyle(
                    fontSize = TextUnit(value = 15f, type = TextUnitType.Sp),
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            )
            IconButton(modifier = Modifier
                .background(
                    color = Color(0xFF770B0B), shape = MaterialTheme.shapes.small
                )
                .weight(0.2f), enabled = false, onClick = {}) {
                Text(
                    color = Color.White,
                    text = eventoUiState.precio.toString() + "€", style = TextStyle(
                        fontSize = TextUnit(value = 15f, type = TextUnitType.Sp),
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )
                )
            }
        }
        TextoInformacion("Fecha y Hora", Icons.Filled.DateRange, eventoUiState.fecha)
        TextoInformacion("Ubicación", Icons.Filled.LocationOn, eventoUiState.lugar)

        Spacer(modifier = Modifier.height(28.dp))
        OutlinedButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { onEventoEvent(EventoEvent.OnIncribirseEvento) }) {
            Text(
                text = "Inscribirse", color = Color(0xAA880000), style = TextStyle(
                    fontSize = TextUnit(value = 18f, type = TextUnitType.Sp)
                )
            )
        }
        if (dialogoUiState.dialogoVisible)
            DialogoInscripcion(dialogoUiState, onDialogoEvent)
        if (dialogoUiState.dialogoComprobacionVisible)
            DialogoConfirmacionInscripcion(dialogoUiState, onDialogoEvent)

    }
}

@Composable
fun LineaSeguidores(eventosUiState: EventoUiState, eventosEvent: (EventoEvent) -> Unit) {
    Row {
        Image(
            modifier = Modifier
                .size(15.dp)
                .weight(0.1f)
                .align(alignment = Alignment.CenterVertically),
            imageVector = Icons.Filled.Person,
            contentDescription = "seguidores"
        )
        Text(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .weight(0.7f),
            text = "Seguidores  ${eventosUiState.seguidores}", style = TextStyle(
                fontSize = TextUnit(value = 15f, type = TextUnitType.Sp),
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        )
        IconButton(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            onClick = { eventosEvent(EventoEvent.OnClickSiguiente) }) {
            Image(
                modifier = Modifier.size(25.dp),
                alignment = Alignment.BottomEnd,
                painter = painterResource(R.drawable.siguiente),
                contentDescription = "Siguiente  evento"
            )
        }
    }
}

@Composable
private fun TextoInformacion(titulo: String, icono: ImageVector, detalle: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = titulo, style = TextStyle(
            fontSize = TextUnit(value = 18f, type = TextUnitType.Sp),
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
    )
    Spacer(modifier = Modifier.height(5.dp))
    Row {
        Image(
            modifier = Modifier.size(15.dp), imageVector = icono, contentDescription = titulo
        )
        Text(
            text = detalle, style = TextStyle(
                fontSize = TextUnit(value = 15f, type = TextUnitType.Sp),
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        )
    }
}

private fun imageResource(imagen: String?) = when (imagen) {
    "imagen1" -> R.drawable.imagen1
    "imagen2" -> R.drawable.imagen2
    "imagen3" -> R.drawable.imagen3
    "imagen4" -> R.drawable.imagen4
    else -> R.drawable.imagen5
}


@Composable
fun ImagenEvento(eventosUiState: EventoUiState, eventosEvent: (EventoEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        /*val contexto = LocalContext.current
        val imageResource = contexto.resources.getIdentifier(
            eventosUiState.imagen, "drawable", contexto.packageName
        )*/
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = imageResource(eventosUiState.imagen)),
            contentDescription = "Imagen de evento",
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.align(alignment = Alignment.BottomEnd)
        ) {
            IconButton(modifier = Modifier.background(
                color = Color(0xAA990000), shape = MaterialTheme.shapes.extraLarge
            ), onClick = { eventosEvent(EventoEvent.OnClickFavoritos) }) {
                var imagen =
                    if (eventosUiState.siguiendo) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                Image(
                    modifier = Modifier,
                    imageVector = imagen,
                    contentDescription = "Favorito"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(modifier = Modifier.background(
                color = Color(0xAA990000), shape = MaterialTheme.shapes.extraLarge
            ), onClick = {

            }) {

                Image(imageVector = Icons.Filled.Share, contentDescription = "Compartir")
            }
        }
    }
}

@Composable
fun CabeceraMenu(modifier: Modifier = Modifier) {
    Box(modifier = modifier.then(Modifier.fillMaxWidth())) {
        Image(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .height(85.dp),
            painter = painterResource(R.drawable.menu),
            contentDescription = "Menu",
            contentScale = ContentScale.Crop
        )
        Image(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .size(width = 125.dp, height = 85.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun DialogoInscripcion(
    dialogoUiState: DialogoUiState,
    onDialogoEvent: (DialogoEvent) -> Unit
) {
    AlertDialog(
        title = { Text(text = "Inscribirse") },
        onDismissRequest = {
            onDialogoEvent(DialogoEvent.onCancelaDialogo)
        },
        confirmButton = {
            TextButton(onClick = {
                onDialogoEvent(DialogoEvent.onAceptaDialogo)
            }) { Text(text = "Confirmar") }
        },
        dismissButton = {
            TextButton(onClick = {
                onDialogoEvent(DialogoEvent.onCancelaDialogo)
            }) { Text(text = "Cancelar") }

        },
        text = {
            Column {
                OutlinedTextFieldName(
                    label = "Nombre",
                    nameState = dialogoUiState.nombre,
                    validacionState = dialogoUiState.validacionNombre,
                    onValueChange = { onDialogoEvent(DialogoEvent.onCambiaNombre(it)) }
                )
                OutlinedTextFieldEmail(
                    label = "Correo",
                    emailState = dialogoUiState.correo,
                    validacionState = dialogoUiState.validacionCorreo,
                    onValueChange = { onDialogoEvent(DialogoEvent.onCambiaCorreo(it)) }
                )
            }
        }
    )

}

@Composable
fun DialogoConfirmacionInscripcion(
    dialogoUiState: DialogoUiState,
    dialogoEvent: (DialogoEvent) -> Unit
) {

    AlertDialog(
        onDismissRequest = { dialogoEvent(DialogoEvent.DialogoComprobacion) },
        confirmButton = {},
        text = {
            Text(text = "${dialogoUiState.nombre} con correo: ${dialogoUiState.correo} se ha inscrito correctamente")
        }
    )
}

@Preview
@Composable
fun PresentacionEventoScreenPreview() {
    PresentacionEventosTheme {
        val viewModel: EventoViewModel = viewModel()
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PresentacionEventoScreen(
                modifier = Modifier.padding(innerPadding),
                dialogoUiState = viewModel.dialogoUiState,
                onDialogoEvent = viewModel::onDialogoEvent,
                eventosUiState = viewModel.eventosUiState[viewModel.eventoVisible],
                onEventosEvent = viewModel::onEventosEvent
            )

        }
    }

}