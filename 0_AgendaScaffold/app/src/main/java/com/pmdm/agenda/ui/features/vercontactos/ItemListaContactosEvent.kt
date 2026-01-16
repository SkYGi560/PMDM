package com.pmdm.agenda.ui.features.vercontactos

import com.pmdm.agenda.ui.features.ContactoUiState

sealed class ItemListaContactosEvent {
    data class OnClickContacto(val contacto : ContactoUiState) : ItemListaContactosEvent()
    object OnDeleteContacto : ItemListaContactosEvent()
}