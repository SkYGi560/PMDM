package com.pmdm.ejemplotextfielviewmodel.ui.features

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pmdm.ejemplotextfielviewmodel.data.mocks.cliente.ClienteRepository
import com.pmdm.ejemplotextfielviewmodel.models.Cliente


class DatosClienteViewModel : ViewModel() {
    val clienteRepository = ClienteRepository()
    var datosClienteUiState by mutableStateOf(
        (clienteRepository.getCliente(dni = "12345678Z") ?: Cliente()).toDatosClienteUiState()
    )
    var validacionDatosClienteUiState by mutableStateOf(ValidacionDatosClienteUiState())
    val validadorDatosCliente = ValidadorDatosCliente()

    fun onDatosClienteEvent(evento: DatosClienteEvent) {
        when (evento) {
            is DatosClienteEvent.OnCambiaNombre -> {
                datosClienteUiState = datosClienteUiState.copy(nombre = evento.nombre)
                validacionDatosClienteUiState =
                    validacionDatosClienteUiState.copy(
                        validacionNombre = validadorDatosCliente.validadorNombre.valida(
                            evento.nombre
                        )
                    )
            }

            is DatosClienteEvent.OnCambiaApellidos -> datosClienteUiState =
                datosClienteUiState.copy(apellidos = evento.apellidos)

            is DatosClienteEvent.OnCambiaDni -> {
                datosClienteUiState = datosClienteUiState.copy(dni = evento.dni)
                validacionDatosClienteUiState = validacionDatosClienteUiState.copy(
                    validacioDNI = validadorDatosCliente.validadorDni.valida(evento.dni)
                )
            }

            is DatosClienteEvent.OnCambiaTelefono -> {
                datosClienteUiState = datosClienteUiState.copy(telefono = evento.telefono)
                validacionDatosClienteUiState = validacionDatosClienteUiState.copy(
                    validacionTelefono = validadorDatosCliente.validadorTelefono.valida(evento.telefono)
                )
            }

            is DatosClienteEvent.OnModificaCliente -> {
                if (!clienteRepository.updateCliente(datosClienteUiState.toCliente())) datosClienteUiState =
                    datosClienteUiState.copy(
                        mostrarDialogo = true,
                        textoDialogo = "El cliente no existe, no se puede modificiar"
                    )
            }

            is DatosClienteEvent.OnAnyadeCliente -> {
                if (!clienteRepository.insert(datosClienteUiState.toCliente())) datosClienteUiState =
                    datosClienteUiState.copy(
                        mostrarDialogo = true,
                        textoDialogo = "El cliente no se pudo añadir"
                    )
            }

            is DatosClienteEvent.OnRecargaDatos
                -> {
                datosClienteUiState = (clienteRepository.getCliente(datosClienteUiState.dni)
                    ?: Cliente()).toDatosClienteUiState()
            }
            is DatosClienteEvent.OnOcultarDialogo -> {
                datosClienteUiState = datosClienteUiState.copy(mostrarDialogo = false)
            }
        }


    }

}

fun Cliente.toDatosClienteUiState() = DatosClienteUiState(id, nombre, apellidos, dni, telefono)
fun DatosClienteUiState.toCliente() = Cliente(id, dni, nombre, apellidos, telefono)