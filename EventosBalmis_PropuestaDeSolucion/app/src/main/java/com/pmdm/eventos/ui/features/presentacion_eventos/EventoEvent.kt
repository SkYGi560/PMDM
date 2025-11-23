package com.pmdm.eventos.ui.features.presentacion_eventos

sealed interface EventoEvent {
    object OnIncribirseEvento : EventoEvent
    object OnClickFavoritos : EventoEvent
    object OnClickSiguiente : EventoEvent
}