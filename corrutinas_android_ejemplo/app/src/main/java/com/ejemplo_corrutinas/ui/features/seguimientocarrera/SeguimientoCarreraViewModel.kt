package com.ejemplo_corrutinas.ui.features.seguimientocarrera

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejemplo_corrutinas.utilities.log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class SeguimientoCarreraViewModel : ViewModel() {
    // Definimos los estados de la UI
    var corredor1 by mutableStateOf(
        CorredorUiState(nombre = "Corredor 1")
    )
        private set
    var corredor2 by mutableStateOf(
        CorredorUiState(nombre = "Corredor 2")
    )
        private set
    var enCarrera by mutableStateOf(false)
        private set

    // Lanza una corrutina que simula el avance de un corredor
    // para ello recibe el estado inicial del corredor que se guarda en el VM
    // una función que actualiza el estado de la UI
    // Devolverá un Job que se puede usar para cancelar la corrutina o esperar a que termine
    private fun CoroutineScope.lanzaCorredor(
        estadoInicialCorredor: CorredorUiState,
        actualizaEstadoUi: (CorredorUiState) -> Unit
    ): Job = launch {
        // Guardamos el estado inicial del corredor en una variable mutable
        // donde se irá actualizando el estado del corredor de forma local
        // este estado servirá para actualizar el estado de la UI del corredor correspondiente
        var estadoCorredorLocal = estadoInicialCorredor
        try {
            coroutineContext.log("CARRERA", "Corredor ${estadoCorredorLocal.nombre} corriendo")

            while (estadoCorredorLocal.porcentajeProgreso < 100) {
                estadoCorredorLocal = estadoCorredorLocal.avanza() // SUSPEND FUN
                actualizaEstadoUi(estadoCorredorLocal)
            }
            coroutineContext.log("CARRERA", "${estadoCorredorLocal.nombre} está en meta")
        } catch (ce: CancellationException) {
            coroutineContext.log("CARRERA", "${estadoCorredorLocal.nombre} se para")
        }
    }

    fun empezarACarrer() =
        // Lanzo una corrutina con el alcance en el que se encuentra el ViewModel
        // y con el contexto de Dispatchers.Default
        viewModelScope.launch(Dispatchers.Default) {
            // Si alguno de los corredores no ha llegado a la meta, sigo en carrera
            if (corredor1.porcentajeProgreso != 100 || corredor2.porcentajeProgreso != 100) {
                enCarrera = true
                try {
                    val jobCorredor1 = lanzaCorredor(corredor1) { corredor1 = it }
                    val jobCorredor2 = lanzaCorredor(corredor2) { corredor2 = it }
                    // Espero a que terminen las dos corrutinas o bien
                    // porque alguno de los corredores ha llagado a la meta
                    // o bien porque se han cancelado las corrutinas
                    joinAll(jobCorredor1, jobCorredor2) // SUSPEND FUN
                    // Si los dos corredores han llegado a la meta, termino la carrera
                    if (corredor1.porcentajeProgreso == 100 && corredor2.porcentajeProgreso == 100)
                        enCarrera = false
                } catch (ce: CancellationException) {
                    enCarrera = false
                }
                viewModelScope.coroutineContext.log("CARRERA", "Corredores parados")
            }
        }

    fun pararDeCorrer() {
        // Cancelo las corrutinas hijas que se estén ejecutando
        // en el contexto del viewModelScope que en mi caso son
        // los dos corredores.
        // Si tuviera más corrutinas, tendrá que guardarme los trabajos
        // como propiedades privadas en el ViewModel y bucarlos en el
        // contexto para cancelarlos de forma específica.
        viewModelScope.coroutineContext.log("CARRERA", "Parando corredores...")
        viewModelScope.coroutineContext.cancelChildren()
    }

    // Gestión de los eventos del Screen
    fun onSeguimientoCarreraEvent(event: SeguimientoCarreraEvent) {
        when (event) {
            SeguimientoCarreraEvent.OnEmpezarPararClick -> {
                if (enCarrera)
                // Cambiamos el estado de enCarrera = false
                // y paramos las corrutinas que avanzan cambiando
                // el valor del estado del progreso
                    pararDeCorrer()
                else
                // Cambiamos el estado de enCarrera = false
                // e iniciamos las corrutinas que avanzan cambiando
                // el valor del estado del progreso
                    empezarACarrer()
            }

            SeguimientoCarreraEvent.OnReiniciarClick -> {
                // Ojo no debería llamar a Reiniciar si estoy en carrera
                // ya que una corrutina suspendida en delay puede actualizar
                // el estado de la UI tras el reset. Esto es porque guarda un
                // el valor de un estado inmutable de la UI anterior.
                corredor1 = corredor1.reinicia()
                corredor2 = corredor2.reinicia()
            }
        }
    }
}