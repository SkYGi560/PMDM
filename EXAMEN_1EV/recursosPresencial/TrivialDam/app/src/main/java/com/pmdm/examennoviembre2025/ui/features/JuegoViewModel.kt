package com.pmdm.examennoviembre2025.ui.features

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pmdm.examennoviembre2025.data.PreguntaRepository
import com.pmdm.examennoviembre2025.models.Pregunta
import com.pmdm.examennoviembre2025.ui.features.inicio.InicioEvent
import com.pmdm.examennoviembre2025.ui.features.inicio.InicioUiState
import com.pmdm.examennoviembre2025.ui.features.inicio.ValidacionInicioUiState
import com.pmdm.examennoviembre2025.ui.features.inicio.ValidadorInicio
import com.pmdm.examennoviembre2025.ui.features.pregunta.PreguntaEvent
import com.pmdm.examennoviembre2025.ui.features.pregunta.PreguntaUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JuegoViewModel @Inject constructor(
    private val preguntaRepository: PreguntaRepository
): ViewModel() {
    var temas = mutableListOf<Int>()
    var puntos = 0
    var preguntas = listOf<PreguntaUiState>()
    var datosPreguntaUiState by mutableStateOf(PreguntaUiState())
    var mostrarPreguntas by mutableStateOf(false)
    var datosInicioUiState by mutableStateOf(InicioUiState(jugadorNombre = "", numeroPreguntas = 10f, estaCategoriaSeleccionada = false))
        private set
    var validacionDatosInicioUiState by mutableStateOf(ValidacionInicioUiState())
    val validadorDatosInicio = ValidadorInicio()

    fun onInicioEvent(evento: InicioEvent){
        when(evento){
            is InicioEvent.onCambiaNombre -> {
                datosInicioUiState = datosInicioUiState.copy(jugadorNombre = evento.nombreJugador)
                validacionDatosInicioUiState =
                    validacionDatosInicioUiState.copy(
                        validacionNombre = validadorDatosInicio.validadorNombre.valida(evento.nombreJugador)
                    )
            }
            is InicioEvent.onCambiaSlider -> {
                datosInicioUiState = datosInicioUiState.copy(numeroPreguntas = evento.numeroPreguntas)
            }
            is InicioEvent.onCategoriaClick -> {
                datosInicioUiState = datosInicioUiState.copy(estaCategoriaSeleccionada = !datosInicioUiState.estaCategoriaSeleccionada)
                if(!datosInicioUiState.estaCategoriaSeleccionada) temas.add(evento.categoria) else temas.remove(evento.categoria)
            }
            is InicioEvent.onJugarClick -> {
                if(!datosInicioUiState.jugadorNombre.isEmpty()
                    && datosInicioUiState.estaCategoriaSeleccionada
                    && validaCantidadPreguntas(preguntaRepository,temas, datosInicioUiState.numeroPreguntas.toInt())){
                    preguntas = preguntaRepository.getPreguntasAleatoriasPor(
                        temas,
                        datosInicioUiState.numeroPreguntas.toInt()).map { it.toPreguntaUiState() }
                    datosPreguntaUiState = datosPreguntaUiState.copy(
                        preguntas[0].pregunta,
                        preguntas[0].opcionCorrecta,
                        preguntas[0].imagenUrl,
                        preguntas[0].tipoPregunta,
                        preguntas[0].respuestas)
                    mostrarPreguntas = true
                }
            }
        }
    }
    fun onPreguntasEvent(evento: PreguntaEvent){
        when(evento){
            is PreguntaEvent.onOculatDialogo -> {
                datosPreguntaUiState = datosPreguntaUiState.copy(mostrarPuntos = false)
            }
            is PreguntaEvent.onBotonAceptarClick -> {
                datosPreguntaUiState = datosPreguntaUiState.copy(mostrarPuntos = true)
                puntos += 5
            }
            is PreguntaEvent.onSeleccionaRespuesta -> {
                datosPreguntaUiState = datosPreguntaUiState.copy(estaSeleccionada = !datosPreguntaUiState.estaSeleccionada)
            }
        }
    }
    fun validaCantidadPreguntas(preguntaRepository: PreguntaRepository, temas: List<Int>, numeroPreguntas: Int): Boolean{
        var preguntas = preguntaRepository.getPreguntasAleatoriasPor(temas,numeroPreguntas)
        var totalPreguntas = 0
        for (i in 0..temas.size -1){
            totalPreguntas +=
                preguntas.count { pregunta -> pregunta.tipoPregunta == temas[i] }
        }
        return totalPreguntas >= numeroPreguntas
    }
    fun Pregunta.toPreguntaUiState() = PreguntaUiState(pregunta,opcionCorrecta,imagenUrl,tipoPregunta,respuestas)
    fun PreguntaUiState.toPregunta() = Pregunta(pregunta,respuestas,opcionCorrecta,imagenUrl,tipoPregunta)
}