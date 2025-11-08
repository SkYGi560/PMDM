package com.pmdm.dialogos.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.dialogos.ui.theme.Dialogos2425Theme
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialogos(
    tareas: List<String>,
    verDialogoTarea: Boolean,
    datePickerState: DatePickerState,
    verDialogoFecha: Boolean,
    tareaTexto: String,
    fechaTexto: String,
    onAbrirDialogoTarea: () -> Unit,
    onCancelarDialogoTarea: () -> Unit,
    onAbrirFecha: () -> Unit,
    onCerrarFecha: () -> Unit,
    onConfirmarFecha: () -> Unit,    // aquí formateas y guardas la fecha
    onCambiaValorTarea: (String) -> Unit,
    onAgregarTarea: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        tareas.forEach {
            Text(it, color = MaterialTheme.colorScheme.background)
            Spacer(Modifier.height(10.dp))
        }

        if (fechaTexto.isNotBlank()) {
            Text("Fecha seleccionada: $fechaTexto")
            Spacer(Modifier.height(10.dp))
        }

        Spacer(Modifier.weight(1f))

        FloatingActionButton(
            onClick = onAbrirDialogoTarea,
            modifier = Modifier.padding(20.dp)
        ) {
            Text("Añadir Tarea", modifier = Modifier.padding(20.dp))
        }

        if (verDialogoTarea) {
            DialogoTarea(
                datePickerState = datePickerState,
                showDateDialog = verDialogoFecha,
                tareaTexto = tareaTexto,
                onAbrirFecha = onAbrirFecha,
                onCerrarFecha = onCerrarFecha,
                onConfirmarFecha = onConfirmarFecha,
                onCambiaValorTarea = onCambiaValorTarea,
                onCancelaDialogoTarea = onCancelarDialogoTarea,
                onConfirmarTarea = onAgregarTarea
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoTarea(
    datePickerState: DatePickerState,
    showDateDialog: Boolean,
    tareaTexto: String,
    onAbrirFecha: () -> Unit,
    onCerrarFecha: () -> Unit,
    onConfirmarFecha: () -> Unit,
    onCambiaValorTarea: (String) -> Unit,
    onCancelaDialogoTarea: () -> Unit,
    onConfirmarTarea: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelaDialogoTarea,
        text = {
            Column {
                Text("Introduce una nueva Tarea")
                OutlinedTextField(
                    value = tareaTexto,
                    onValueChange = onCambiaValorTarea
                )
                TextButton(onClick = onAbrirFecha) {
                    Text("Añadir Fecha de tarea")
                }

                if (showDateDialog) {
                    DatePickerDialog(
                        onDismissRequest = onCerrarFecha,
                        confirmButton = {
                            TextButton(onClick = onConfirmarFecha) { Text("Ok") }
                        },
                        dismissButton = {
                            TextButton(onClick = onCerrarFecha) { Text("Cancel") }
                        },

                    ) {
                        DatePicker(state = datePickerState)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirmarTarea) { Text("Añadir Tarea") }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NoseqPreview() {
    var tareas by remember { mutableStateOf(listOf("Tarea uno","Tarea dos","Tarea tres","Tarea cuatro")) }
    var verDialogoTarea by remember { mutableStateOf(false) }
    var verDialogoFecha by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var tareaTexto by rememberSaveable { mutableStateOf("") }
    var fechaTexto by rememberSaveable { mutableStateOf("") }

    fun formatearFechaSeleccionada() {
        if(datePickerState.selectedDateMillis!=null) {
            val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = datePickerState.selectedDateMillis!!
            fechaTexto=formatter.format(calendar.time)
        }
    }

    Dialogos(
        tareas = tareas,
        verDialogoTarea = verDialogoTarea,
        datePickerState = datePickerState,
        verDialogoFecha = verDialogoFecha,
        tareaTexto = tareaTexto,
        fechaTexto = fechaTexto,
        onAbrirDialogoTarea = { verDialogoTarea = true },
        onCancelarDialogoTarea = { verDialogoTarea = false },
        onAbrirFecha = { verDialogoFecha = true },
        onCerrarFecha = { verDialogoFecha = false },
        onConfirmarFecha = { formatearFechaSeleccionada() },
        onCambiaValorTarea = { tareaTexto = it },
        onAgregarTarea = {
            if (tareaTexto.isNotBlank()) {
                tareas = tareas + "$tareaTexto${if (fechaTexto.isNotBlank()) " ($fechaTexto)" else ""}"
                tareaTexto = ""
                verDialogoTarea = false
            }
        }
    )
}
