package com.pmdm.examennoviembre2025.data

import com.pmdm.examennoviembre2025.data.mocks.pregunta.PreguntaDaoMock
import com.pmdm.examennoviembre2025.models.Pregunta
import javax.inject.Inject

class PreguntaRepository @Inject constructor(
    private val preguntaDaoMock: PreguntaDaoMock
){
    fun getPreguntasAleatoriasPor(temas: List<Int>, nivel: Int): List<Pregunta> =
        preguntaDaoMock.getPreguntasAleatoriasPor(temas = temas, nivel = nivel).map { it.toPregunta() }
}