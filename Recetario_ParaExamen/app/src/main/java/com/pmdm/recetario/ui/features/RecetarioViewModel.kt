package com.pmdm.recetario.ui.features

import android.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.recetario.data.RecetarioRepository
import com.pmdm.recetario.data.aRecetaEntity
import com.pmdm.recetario.model.Receta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecetarioViewModel @Inject constructor(
    private val recetarioRepository: RecetarioRepository
) : ViewModel() {

    var recetaSeleccionadaState: Receta? by mutableStateOf(null)
        private set
    var listaRecetasState: List<Receta> by mutableStateOf(mutableListOf())
        private set
    var filtroState: FiltrosRecetas by mutableStateOf(FiltrosRecetas.Todas)
        private set

    var listaChefs: List<String> by mutableStateOf(mutableListOf())
        private set

    var chefSeleccionado: String? by mutableStateOf(null)

    suspend fun valoresChefs(){
        listaChefs = recetarioRepository.getChefs()
    }

    suspend fun refreshList(){
        listaRecetasState = when(filtroState){
            FiltrosRecetas.Todas -> recetarioRepository.getTodas()
            FiltrosRecetas.PorChef -> recetarioRepository.getPorChef(chefSeleccionado!!)
            FiltrosRecetas.Favoritas -> recetarioRepository.getFavoritas()
        }
    }

    fun onRecetarioEvent(e: RecetarioEvent) {
        when(e){
            RecetarioEvent.OnBorrarClick -> viewModelScope.launch {
                recetarioRepository.delete(recetaSeleccionadaState!!.id)
                refreshList()
            }
            is RecetarioEvent.OnFiltroClick -> viewModelScope.launch {
                filtroState = e.filtro
                refreshList()
            }
            RecetarioEvent.OnMarcarFavoritaClick -> onFavoritaClick()

            is RecetarioEvent.OnSeleccionarReceta -> viewModelScope.launch {
                recetaSeleccionadaState = recetarioRepository.getPorId(e.id)
            }
        }
    }

    fun onFavoritaClick(){
        viewModelScope.launch {
            recetaSeleccionadaState = recetaSeleccionadaState!!.copy(favorita = !recetaSeleccionadaState!!.favorita)
            recetarioRepository.update(recetaSeleccionadaState!!)
            refreshList()
        }
    }

    fun onSeleccionaChef(chef: String){
        chefSeleccionado = chef
        viewModelScope.launch {
            refreshList()
        }
    }

    init {
        viewModelScope.launch {
            refreshList()
            valoresChefs()
        }
    }
}