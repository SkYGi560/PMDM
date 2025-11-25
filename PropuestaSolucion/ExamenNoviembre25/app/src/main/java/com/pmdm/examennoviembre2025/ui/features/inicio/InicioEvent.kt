package com.pmdm.examennoviembre2025.ui.features.inicio

sealed interface InicioEvent {
    object OnIniciaJuego : InicioEvent
    object OnCambiaEstadoDialogoAviso : InicioEvent
    data class OnCambiaNombre(val nombre: String) : InicioEvent
    data class OnSeleccionaTemas(val tema: String) : InicioEvent

    data  class OnSeleccionaNivel(val nivel: Int): InicioEvent
}