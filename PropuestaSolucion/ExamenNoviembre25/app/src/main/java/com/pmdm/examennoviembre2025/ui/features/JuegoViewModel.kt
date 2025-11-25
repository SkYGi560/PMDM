package com.pmdm.examennoviembre2025.ui.features

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.pmdm.examennoviembre2025.data.PreguntaRepository
import com.pmdm.examennoviembre2025.models.Pregunta
import com.pmdm.examennoviembre2025.ui.features.inicio.InicioEvent
import com.pmdm.examennoviembre2025.ui.features.pregunta.PreguntaEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JuegoViewModel @Inject constructor( private val preguntaRepository: PreguntaRepository): ViewModel() {

    var preguntas by mutableStateOf(listOf<Pregunta>())
    var juegoUiState by mutableStateOf(JuegoUiState())
    var numeroPreguntaActual by mutableStateOf(0)
    var validacion by mutableStateOf(object : Validacion {} as Validacion)


    fun onInicioEvent(evento: InicioEvent) {

        when (evento) {
            is InicioEvent.OnIniciaJuego -> {

                if (puedeIniciarJuego()) {
                    preguntas = preguntaRepository.getPreguntasAleatoriasPor(
                        temas = juegoUiState.temas.toEnteros(), nivel = juegoUiState.nivel
                    )

                    juegoUiState = juegoUiState.copy(
                        modoInicio = false, cuestion = preguntas[0]
                    )
                } else {
                    juegoUiState = juegoUiState.copy(muestraDialogoAviso = true)
                }
            }

            is InicioEvent.OnCambiaNombre -> {
                juegoUiState = juegoUiState.copy(jugador = evento.nombre)
                validacion = object : Validacion {
                    override val hayError: Boolean
                        get() = evento.nombre.isEmpty()
                    override val mensajeError: String?
                        get() = "El nombre no puede estar vacio"
                }
            }

            is InicioEvent.OnSeleccionaTemas -> if (juegoUiState.temas.contains(evento.tema)) juegoUiState =
                juegoUiState.copy(temas = quitaTemaDeLista(evento.tema))
            else juegoUiState = juegoUiState.copy(temas = añadeTemaALista(evento.tema))


            is InicioEvent.OnSeleccionaNivel -> juegoUiState =
                juegoUiState.copy(nivel = evento.nivel)

            is InicioEvent.OnCambiaEstadoDialogoAviso -> juegoUiState =
                juegoUiState.copy(muestraDialogoAviso = !juegoUiState.muestraDialogoAviso)

        }
    }

    fun onPreguntaEvent(evento: PreguntaEvent) {
        when (evento) {
            is PreguntaEvent.OnOpcionSeleccionada -> {
                if (juegoUiState.cuestion.opcionCorrecta == evento.seleccionada) juegoUiState =
                    juegoUiState.copy(puntos = juegoUiState.puntos + 5)
                else if (evento.seleccionada != -1) juegoUiState =
                    juegoUiState.copy(puntos = juegoUiState.puntos - 5)
            }

            is PreguntaEvent.OnSiguiente -> {
                if (numeroPreguntaActual + 1 < preguntas.size) juegoUiState =
                    juegoUiState.copy(cuestion = preguntas[++numeroPreguntaActual])
                else juegoUiState = juegoUiState.copy(muestraDialogoPuntos = true)
            }

            is PreguntaEvent.OnCambiaEstadoDialogoPuntos -> juegoUiState =
                juegoUiState.copy(muestraDialogoPuntos = !juegoUiState.muestraDialogoPuntos)

            is PreguntaEvent.OnVolver -> {
                juegoUiState =
                    juegoUiState.copy(modoInicio = true, jugador = "", puntos = 0, temas = listOf())
                numeroPreguntaActual = 0
            }

        }
    }

    private fun puedeIniciarJuego(): Boolean {
        if (juegoUiState.jugador.isEmpty()) juegoUiState =
            juegoUiState.copy(textoDialogoAviso = "El nombre no puede ser vacío")
        else if (juegoUiState.temas.size == 0) juegoUiState =
            juegoUiState.copy(textoDialogoAviso = "Debes escoger al menos un tema")
        else if (juegoUiState.nivel > juegoUiState.temas.size * 10) juegoUiState =
            juegoUiState.copy(
                textoDialogoAviso = "Tienes pocos temas seleccionados\n para el nivel de preguntas escogido"
            )
        else return true
        return false;
    }

    private fun quitaTemaDeLista(tema: String): List<String> {
        var listaNueva = mutableListOf<String>()
        juegoUiState.temas.forEach { if (it != tema) listaNueva.add(it) }
        return listaNueva
    }

    private fun añadeTemaALista(tema: String): List<String> {
        var listaNueva = juegoUiState.temas.toMutableList()
        listaNueva.add(tema)
        return listaNueva
    }
}

private fun List<String>.toEnteros(): List<Int> {
    var listaEnteros = mutableListOf<Int>()
    this.map {
        listaEnteros.add(
            when (it) {
                "Arte" -> 1
                "Geografía" -> 2
                "Historia" -> 3
                "Entretenimiento" -> 4
                else -> 0
            }
        )
    }
    return listaEnteros
}


