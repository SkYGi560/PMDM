package com.pmdm.examennoviembre2025.ui.features.pregunta

interface PreguntaEvent {
    object onSeleccionaRespuesta: PreguntaEvent
    object onBotonAceptarClick : PreguntaEvent
    object onOculatDialogo : PreguntaEvent
}