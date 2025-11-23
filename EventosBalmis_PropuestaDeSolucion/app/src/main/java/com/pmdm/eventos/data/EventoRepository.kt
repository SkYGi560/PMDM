package com.pmdm.eventos.data

import com.pmdm.eventos.data.mocks.EventoDaoMock

import com.pmdm.eventos.models.Evento
import javax.inject.Inject

class EventoRepository @Inject constructor(
    private val eventosDaoMock: EventoDaoMock
) {
    fun get(): List<Evento> = eventosDaoMock.get().toEventos()
    fun incrementaSeguidor(evento: Evento) = eventosDaoMock.incrementaSeguidor(evento.toEventoMock())
    fun decrementaSeguidor(evento: Evento) = eventosDaoMock.decrementaSeguidor(evento.toEventoMock())
}