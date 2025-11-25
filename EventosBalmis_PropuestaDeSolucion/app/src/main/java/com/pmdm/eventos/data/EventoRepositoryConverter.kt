package com.pmdm.eventos.data

import com.pmdm.eventos.data.mocks.EventoMock
import com.pmdm.eventos.models.Evento

fun EventoMock.toEvento() = Evento(
        this.imagen,
        this.titulo,
        this.fecha,
        this.lugar,
        this.realizado,
        this.precio,
        this.seguidores
    )

fun List<EventoMock>.toEventos() = this.map { it.toEvento() }

fun Evento.toEventoMock() = EventoMock(
    this.imagen,
    this.titulo,
    this.fecha,
    this.lugar,
    this.realizado,
    this.precio,
    this.seguidores
)