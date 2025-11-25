package com.pmdm.eventos.ui.features.presentacion_eventos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.pmdm.eventos.data.EventoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventoViewModel @Inject constructor(
    private val eventosRepository: EventoRepository
):ViewModel(){

    var eventosUiState by mutableStateOf(eventosRepository.get().toEventosUiState().toMutableStateList())
    var eventoVisible by mutableIntStateOf(0)
    var dialogoUiState: DialogoUiState by  mutableStateOf(DialogoUiState())

    fun onDialogoEvent(dialogoEvent: DialogoEvent) = when(dialogoEvent)
    {
        is DialogoEvent.onCancelaDialogo -> {
            dialogoUiState= dialogoUiState.copy(dialogoVisible = false)
        }
        is DialogoEvent.onAceptaDialogo ->
        {
            dialogoUiState= dialogoUiState.copy( dialogoComprobacionVisible=true)
            dialogoUiState= dialogoUiState.copy(dialogoVisible = false)
        }
        is DialogoEvent.DialogoComprobacion ->
        {
            dialogoUiState= dialogoUiState.copy( dialogoComprobacionVisible=false)
            dialogoUiState = dialogoUiState.copy(nombre = "")
            dialogoUiState = dialogoUiState.copy(correo = "")
        }
        is DialogoEvent.onCambiaNombre -> {
            dialogoUiState = dialogoUiState.copy(nombre = dialogoEvent.nombre)
            dialogoUiState = dialogoUiState.copy( validacionNombre =
            object : Validacion{
                override val hayError: Boolean
                    get() = dialogoEvent.nombre.isEmpty()
                override val mensajeError: String?
                    get() ="No puede estar vacio"
            })
        }
        is DialogoEvent.onCambiaCorreo -> {
            dialogoUiState = dialogoUiState.copy(correo = dialogoEvent.correo)
            dialogoUiState = dialogoUiState.copy(validacionCorreo =
            object : Validacion{
                override val hayError: Boolean
                    get() = !dialogoEvent.correo.matches(Regex("^[A-Za-z].*@.+\\..+$")) || dialogoEvent.correo.isEmpty()
                override val mensajeError: String?
                    get() = if(dialogoEvent.correo.isEmpty()) "El correo no puede ser vacio"
                else "El correo debe tener un formato correcto"
            }

            )
        }
    }

    fun onEventosEvent(eventosEvent: EventoEvent){
        when(eventosEvent){
            is EventoEvent.OnIncribirseEvento -> {
                dialogoUiState= dialogoUiState.copy(dialogoVisible = true)
            }
            is EventoEvent.OnClickFavoritos -> {
                eventosUiState[eventoVisible]=eventosUiState[eventoVisible].copy(siguiendo=!eventosUiState[eventoVisible].siguiendo)
                if(eventosUiState[eventoVisible].siguiendo)
                    eventosRepository.incrementaSeguidor(eventosUiState[eventoVisible].toEvento())
                else eventosRepository.decrementaSeguidor(eventosUiState[eventoVisible].toEvento())
                eventosUiState[eventoVisible]=eventosUiState[eventoVisible].copy(seguidores = eventosRepository.get()[eventoVisible].seguidores)
            }
            is EventoEvent.OnClickSiguiente ->{
               if(eventoVisible < eventosUiState.size-1) eventoVisible ++
                else eventoVisible = 0
            }
        }
    }
}