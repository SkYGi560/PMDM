package com.pmdm.agenda.features.vercontactos

import ValidacionContactoUiState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.agenda.data.ContactoRepository
import com.pmdm.agenda.features.ContactoUiState
import com.pmdm.agenda.features.ValidadorContacto
import com.pmdm.agenda.features.toContactoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaContactosViewModel @Inject constructor(
    private val repository : ContactoRepository,
    private val validadorContacto: ValidadorContacto
) : ViewModel() {
    var validacionContactoState by mutableStateOf(ValidacionContactoUiState())
    var contatoSleccionadoState: ContactoUiState? by mutableStateOf(null)
        private set
    private var _listaContactosState by mutableStateOf(mutableListOf<ContactoUiState>())
    val listaContactosState: List<ContactoUiState>
        get() = _listaContactosState

    private suspend fun getContactos(): MutableList<ContactoUiState> =
        repository.get().map { it.toContactoUiState() }.toMutableList()

    init {
        viewModelScope.launch {
            _listaContactosState = getContactos()
        }
    }
    fun setContactoState(idContacto: Int) {
            viewModelScope.launch {
                contatoSleccionadoState = repository.get(idContacto)?.toContactoUiState()
            }
        }

    fun onItemListaContatoEvent(e: ItemListaContactosEvent) {
        when (e) {
            is ItemListaContactosEvent.OnClickContacto -> {
                viewModelScope.launch {
                    contatoSleccionadoState =
                        if (contatoSleccionadoState?.id != e.contacto.id) e.contacto else null
                }
            }
            is ItemListaContactosEvent.OnDeleteContacto -> {
                viewModelScope.launch {
                    repository.delete(contatoSleccionadoState!!.id)
                    _listaContactosState = getContactos()
                }

            }
        }
    }
}