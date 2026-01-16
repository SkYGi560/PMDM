package com.pmdm.menulateral.ui.features

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pmdm.menulateral.data.mocks.ContactoRepository
import com.pmdm.menulateral.models.Contacto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrabajoViewModel @Inject constructor(
    contactoRepository: ContactoRepository
) :
    ViewModel() {
    var selectedTabIndex by mutableIntStateOf(0)

    val lista by mutableStateOf(
        contactoRepository.getTodos().map { Contacto(it.id, it.nombre, it.tipo) }
    )

    val onSelectedTabIndex: (Int) -> Unit = { iSeleccioando ->
        selectedTabIndex = iSeleccioando
    }

    fun getContacto(idContacto: Int) = lista.find { it.id == idContacto }
}