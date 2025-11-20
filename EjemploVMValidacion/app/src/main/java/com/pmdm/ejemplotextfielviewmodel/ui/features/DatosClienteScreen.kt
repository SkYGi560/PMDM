package com.pmdm.ejemplotextfielviewmodel.ui.features

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldName
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldPhone
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldWithErrorState


@Composable
fun DatosClienteScreen(
    modifier: Modifier = Modifier,
    datosClienteUiState: DatosClienteUiState,
    datosClienteValidacion: ValidacionDatosClienteUiState,
    onDatosClienteEvent: (DatosClienteEvent) -> Unit,
) {


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            if (datosClienteUiState.nombre.isNotBlank()) {
                Text(
                    text = datosClienteUiState.nombre,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar imagen",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextFieldWithErrorState(
            label = "Dni",
            textoState = datosClienteUiState.dni,
            textoPista = "Introduce un dni correcto",
            validacionState = datosClienteValidacion.validacioDNI,
            onValueChange = { onDatosClienteEvent(DatosClienteEvent.OnCambiaDni(it)) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextFieldName(
            nameState = datosClienteUiState.nombre,
            validacionState = datosClienteValidacion.validacionNombre,
            onValueChange = { onDatosClienteEvent(DatosClienteEvent.OnCambiaNombre(it)) }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = datosClienteUiState.apellidos,
            onValueChange = { onDatosClienteEvent(DatosClienteEvent.OnCambiaApellidos(it)) },
            label = { Text(text = "Apellidos") },
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextFieldPhone(
            telefonoState = datosClienteUiState.telefono,
            validacionState = datosClienteValidacion.validacionTelefono,
            onValueChange = { onDatosClienteEvent(DatosClienteEvent.OnCambiaTelefono(it)) }
        )


        Spacer(modifier = Modifier.height(8.dp))
        Row() {
            Button(onClick = { onDatosClienteEvent(DatosClienteEvent.OnAnyadeCliente) }) { Text(text = "Añade") }
            Spacer(modifier = Modifier.width(4.dp))
            Button(onClick = { onDatosClienteEvent(DatosClienteEvent.OnRecargaDatos) }) { Text(text = "Recargar") }
            Spacer(modifier = Modifier.width(4.dp))
            Button(onClick = { onDatosClienteEvent(DatosClienteEvent.OnModificaCliente) }) {
                Text(
                    text = "Modifica"
                )
            }
        }
    }
    if (datosClienteUiState.mostrarDialogo) {
        AlertDialog(
            title = {
                Column() {
                    Icon(imageVector = Icons.Filled.Warning, contentDescription = "Aviso")
                    Text(
                        text = datosClienteUiState.textoDialogo,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            },
            onDismissRequest = { onDatosClienteEvent(DatosClienteEvent.OnOcultarDialogo) },
            confirmButton = {})
    }
}

@Preview(showBackground = true)
@Composable
fun DatosClienteScreenPreview() {
    val ejemplo = DatosClienteUiState(
        id = 1,
        dni = "12345678Z",
        nombre = "Ana",
        apellidos = "Gacía García",
        telefono = "600-123-001"
    )
    val validacionDatosClienteUiState = ValidacionDatosClienteUiState()
    DatosClienteScreen(
        datosClienteUiState = ejemplo,
        datosClienteValidacion = validacionDatosClienteUiState,
        onDatosClienteEvent = {})
}
