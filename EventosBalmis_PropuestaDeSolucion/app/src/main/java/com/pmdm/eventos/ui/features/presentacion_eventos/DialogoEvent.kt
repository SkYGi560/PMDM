package com.pmdm.eventos.ui.features.presentacion_eventos

sealed interface DialogoEvent {
    object onCancelaDialogo : DialogoEvent
    object onAceptaDialogo : DialogoEvent
    object DialogoComprobacion : DialogoEvent
    data class onCambiaNombre(val nombre: String) : DialogoEvent
    data class onCambiaCorreo(val correo: String) : DialogoEvent

}