package com.pmdm.recetario.ui.features

sealed interface RecetarioEvent {
    data class OnSeleccionarReceta(val id: Int) : RecetarioEvent


    object OnMarcarFavoritaClick : RecetarioEvent

    object OnBorrarClick : RecetarioEvent

    data class OnFiltroClick(val filtro : FiltrosRecetas) : RecetarioEvent

}