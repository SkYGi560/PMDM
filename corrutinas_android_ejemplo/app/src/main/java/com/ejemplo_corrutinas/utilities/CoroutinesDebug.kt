package com.ejemplo_corrutinas.utilities

import android.util.Log
import kotlinx.coroutines.job
import kotlin.coroutines.CoroutineContext

fun CoroutineContext.log(
    corrutina: String = "Corrutina",
    accion: String = "No especificada"
) {
    Log.println(
        Log.DEBUG, corrutina,
        "${corrutina}: {\n\tAccion: ${accion}, \n\tContexto: ${this}\n}"
    )
}