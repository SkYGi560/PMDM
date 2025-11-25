package com.pmdm.examennoviembre2025.ui.features.pregunta

import com.pmdm.examennoviembre2025.ui.features.inicio.InicioEvent

sealed interface PreguntaEvent {
    data class OnOpcionSeleccionada(val seleccionada: Int) : PreguntaEvent
    object OnSiguiente : PreguntaEvent
    object OnCambiaEstadoDialogoPuntos : PreguntaEvent
    object OnVolver : PreguntaEvent

}