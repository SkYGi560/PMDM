package com.pmdm.agenda.features.vercontactos

import com.pmdm.agenda.features.ContactoUiState

sealed class ItemListaContactosEvent {
    data class OnClickContacto(val contacto : ContactoUiState) : ItemListaContactosEvent()
    object OnDeleteContacto : ItemListaContactosEvent()
}