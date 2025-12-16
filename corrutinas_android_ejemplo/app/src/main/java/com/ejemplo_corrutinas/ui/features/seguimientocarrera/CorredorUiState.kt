package com.ejemplo_corrutinas.ui.features.seguimientocarrera

import kotlinx.coroutines.delay
import kotlin.coroutines.coroutineContext

data class CorredorUiState(
    val nombre: String,
    val porcentajeProgreso : Int = 0
) {
    // Función de suspensión que simula el avance del corredor en la
    // carrera bloquerá la corrutina con una espera entre 5 y 300 ms
    suspend fun avanza(): CorredorUiState {
        delay((5L..300L).random())
        val nuevoPorcentaje = porcentajeProgreso + 1
        return copy(porcentajeProgreso = nuevoPorcentaje)
    }

    fun reinicia(): CorredorUiState = copy(porcentajeProgreso = 0)
}
