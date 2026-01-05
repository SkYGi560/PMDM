package com.pmdm.leefichero.ui.features

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class LectorViewModel(application: Application) : AndroidViewModel(application) {

    var textoBuilder by mutableStateOf(StringBuilder())
        private set

    var progreso by mutableFloatStateOf(0f)
        private set

    var cargando by mutableStateOf(false)
        private set

    fun cargarDesdeUri(uri: Uri) {
        textoBuilder = StringBuilder()
        progreso = 0f
        cargando = true

        viewModelScope.launch {
            val contenido = leeFichero(uri).orEmpty()
            val total = contenido.length.coerceAtLeast(1)

            val sb = StringBuilder()

            for (i in contenido.indices) {
                sb.append(contenido[i])

                // 👇 IMPORTANTÍSIMO: cambiar referencia para que Compose recompose
                textoBuilder = StringBuilder(sb)

                progreso = (i + 1).toFloat() / total.toFloat()
                delay(1)
            }

            cargando = false
        }
    }

    @Throws(IOException::class)
    private fun leeFichero(uri: Uri): String? {
        val contentResolver = getApplication<Application>().contentResolver
        contentResolver.openInputStream(uri)?.use { inputStream ->
            return BufferedReader(InputStreamReader(inputStream)).readText()
        }
        return null
    }
}
