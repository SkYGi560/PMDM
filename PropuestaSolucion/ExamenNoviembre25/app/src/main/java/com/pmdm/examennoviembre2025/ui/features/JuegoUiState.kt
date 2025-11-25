package com.pmdm.examennoviembre2025.ui.features

import com.pmdm.examennoviembre2025.models.Pregunta
import kotlin.enums.enumEntries

data class JuegoUiState(
    val cuestion: Pregunta = Pregunta(),
    val jugador: String = "",
    val puntos: Int = 0,
    val nivel: Int = 10,
    val temas: List<String> = listOf(),
    val modoInicio: Boolean = true,
    val muestraDialogoAviso: Boolean = false,
    val textoDialogoAviso: String = "",
    val muestraDialogoPuntos: Boolean = false
)






