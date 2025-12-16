package com.pmdm.examennoviembre2025.ui.features.inicio

sealed interface InicioEvent {
    data class onCategoriaClick(val categoria: Int) : InicioEvent
    data class onCambiaNombre(val nombreJugador: String) : InicioEvent
    data class  onCambiaSlider(val numeroPreguntas: Float) : InicioEvent
    object onJugarClick : InicioEvent
}