package com.pmdm.examennoviembre2025.data

import com.pmdm.examennoviembre2025.data.mocks.pregunta.PreguntaMock
import com.pmdm.examennoviembre2025.models.Pregunta

fun PreguntaMock.toPregunta(): Pregunta =  Pregunta(pregunta,respuestas,opcionCorrecta,imagenUrl)

fun List<PreguntaMock>.toPreguntas(): List<Pregunta> = this.map { it.toPregunta() }
