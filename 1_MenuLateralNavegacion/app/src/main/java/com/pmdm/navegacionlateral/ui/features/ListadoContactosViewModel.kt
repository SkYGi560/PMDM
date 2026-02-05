package com.pmdm.navegacionlateral.ui.features

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pmdm.navegacionlateral.data.mocks.ContactoRepository
import com.pmdm.navegacionlateral.models.Contacto
import com.pmdm.navegacionlateral.models.TipoContacto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListadoContactosViewModel @Inject constructor(
    private val contactoRepository: ContactoRepository
) : ViewModel() {

    var itemSeleccionado by mutableIntStateOf(0)

    var visualizarbottomBar by mutableStateOf(true)

    var lista by mutableStateOf(
        contactoRepository.getTodos()
            .map { 
                Contacto(it.id, it.nombre, it.tipo) 
            })

    val onNavigateTo: (TipoContacto) -> Unit = {
        visualizarbottomBar = it != TipoContacto.Trabajo
    }

    val onItemSeleccionado: (Int) -> Unit = { iSelecionado ->
            visualizarbottomBar = true
            itemSeleccionado = iSelecionado
            lista = when (iSelecionado) {
                1 -> contactoRepository.getAmigos()
                    .map { Contacto(it.id, it.nombre, it.tipo) }
                2 -> contactoRepository.getCoro()
                    .map { Contacto(it.id, it.nombre, it.tipo) }
                3 -> contactoRepository.getFamilia()
                    .map { Contacto(it.id, it.nombre, it.tipo) }
                4 -> contactoRepository.getTrabajo()
                    .map { Contacto(it.id, it.nombre, it.tipo) }
                5 -> contactoRepository.getVecinos()
                    .map { Contacto(it.id, it.nombre, it.tipo) }
                else -> contactoRepository.getTodos()
                    .map { Contacto(it.id, it.nombre, it.tipo) }
            }
        }
}