package com.pmdm.examennoviembre2025.data

import com.pmdm.examennoviembre2025.data.mocks.pregunta.PreguntaMock
import com.pmdm.examennoviembre2025.models.Pregunta

fun Pregunta.toPreguntaMock(): PreguntaMock = PreguntaMock(
    pregunta = pregunta,
    respuestas = respuestas,
    opcionCorrecta = opcionCorrecta,
    imagenUrl = imagenUrl,
    tipoPregunta = tipoPregunta
)

fun PreguntaMock.toPregunta(): Pregunta = Pregunta(
    pregunta = pregunta,
    respuestas = respuestas,
    opcionCorrecta = opcionCorrecta,
    imagenUrl = imagenUrl,
    tipoPregunta = tipoPregunta
)