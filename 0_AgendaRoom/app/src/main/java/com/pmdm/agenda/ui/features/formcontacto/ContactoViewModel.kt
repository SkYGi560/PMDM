package com.pmdm.agenda.ui.features.formcontacto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.pmdmiesbalmis.components.manejo_errores.InformacionEstadoUiState
import com.pmdm.agenda.data.ContactoRepository
import com.pmdm.agenda.models.Contacto
import com.pmdm.agenda.ui.features.ContactoUiState
import com.pmdm.agenda.ui.features.toContacto
import com.pmdm.agenda.ui.features.toContactoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ContactoViewModel @Inject constructor(
    private val contactoRepository: ContactoRepository,
    private val validadorContacto: ValidadorContacto
) : ViewModel() {
    class ContactoViewModelException(message: String) : Exception(message)

    var editandoContactoExistenteState: Boolean = false
        private set

    var contactoState by mutableStateOf(ContactoUiState())
        private set
    var validacionContactoState by mutableStateOf(ValidacionContactoUiState())
        private set
    var informacionEstadoState: InformacionEstadoUiState by mutableStateOf(
        InformacionEstadoUiState.Oculta())
        private set

    fun setContactoState(idContacto: Int?) {
        // Solo actualizo el estado si el id es distinto al actual y es distinto de null
        if (idContacto != null && idContacto != contactoState.id) {
            viewModelScope.launch {
                editandoContactoExistenteState = true
                val c: Contacto = contactoRepository.get(idContacto)
                    ?: throw ContactoViewModelException("El id $idContacto no existe en la base de datos")
                contactoState = c.toContactoUiState()
                validacionContactoState = validadorContacto.valida(contactoState)
            }
        }
    }

    fun clearContactoState() {
        editandoContactoExistenteState = false
        contactoState = ContactoUiState()
        validacionContactoState = ValidacionContactoUiState()
    }

    fun onContactoEvent(e: ContactoEvent) {
        when (e) {
            is ContactoEvent.OnChangeCategoria -> {
                contactoState = contactoState.copy(categorias = e.categoria)
            }

            is ContactoEvent.OnChangeNombre -> {
                contactoState = contactoState.copy(nombre = e.nombre)
                validacionContactoState = validacionContactoState.copy(
                    validacionNombre = validadorContacto.validadorNombre.valida(e.nombre)
                )
            }

            is ContactoEvent.OnChangeApellidos -> {
                contactoState = contactoState.copy(apellidos = e.apellidos)
                validacionContactoState = validacionContactoState.copy(
                    validacionApellidos = validadorContacto.validadorApellidos.valida(e.apellidos)
                )
            }

            is ContactoEvent.OnChangeCorreo -> {
                contactoState = contactoState.copy(correo = e.correo)
                validacionContactoState = validacionContactoState.copy(
                    validacionCorreo = validadorContacto.validadorCorreo.valida(e.correo)
                )
            }

            is ContactoEvent.OnChangeTelefono -> {
                contactoState = contactoState.copy(telefono = e.telefono)
                validacionContactoState = validacionContactoState.copy(
                    validacionTelefono = validadorContacto.validadorTelefono.valida(e.telefono)
                )
            }

            is ContactoEvent.OnChangeFoto -> {
                contactoState = contactoState.copy(foto = e.foto)
            }

            is ContactoEvent.OnDismissError -> {
                validacionContactoState = ValidacionContactoUiState()
            }

            is ContactoEvent.OnSaveContacto -> {
                validacionContactoState = validadorContacto.valida(contactoState)
                if (!validacionContactoState.hayError) {
                    val c: Contacto = contactoState.toContacto()
                    // Lo ejecuto de forma bloqueante para evitar efecto colateral
                    // más adelante al usar flows se solucionará el problema y podremos
                    // hacerlo mediante una corrutina
                    runBlocking {
                        if (editandoContactoExistenteState) {
                            contactoRepository.update(c)
                        } else {
                            contactoRepository.insert(c)
                        }
                    }
                    e.onNavigateTrasFormContacto(true)
                } else {
                    informacionEstadoState = InformacionEstadoUiState.Error(
                        mensaje = validacionContactoState.mensajeError!!,
                        onDismiss = { informacionEstadoState = InformacionEstadoUiState.Oculta() }
                    )
                }
            }
        }
    }
}
