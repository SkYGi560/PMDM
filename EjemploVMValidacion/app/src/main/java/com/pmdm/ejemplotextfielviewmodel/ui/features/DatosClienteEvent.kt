package com.pmdm.ejemplotextfielviewmodel.ui.features

sealed interface DatosClienteEvent {
    data class OnCambiaNombre(val nombre: String) : DatosClienteEvent
    data class OnCambiaApellidos(val apellidos: String) : DatosClienteEvent
    data class OnCambiaDni(val dni: String) : DatosClienteEvent
    data class OnCambiaTelefono(val telefono: String) : DatosClienteEvent
    object OnAnyadeCliente : DatosClienteEvent
    object OnModificaCliente : DatosClienteEvent
    object OnRecargaDatos : DatosClienteEvent
    object OnOcultarDialogo : DatosClienteEvent
}