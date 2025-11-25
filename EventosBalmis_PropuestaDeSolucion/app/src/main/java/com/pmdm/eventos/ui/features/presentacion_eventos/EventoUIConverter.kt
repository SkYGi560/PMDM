package com.pmdm.eventos.ui.features.presentacion_eventos

import com.pmdm.eventos.models.Evento

fun Evento.toEventoUiState() = EventoUiState(
        this.imagen,
        this.titulo,
        this.fecha,
        this.lugar,
        this.realizado,
        this.precio,
        this.seguidores
    )

fun List<Evento>.toEventosUiState() = this.map { it.toEventoUiState() }

fun EventoUiState.toEvento() = Evento(
    this.imagen,
    this.titulo,
    this.fecha,
    this.lugar,
    this.realizado,
    this.precio,
    this.seguidores
)