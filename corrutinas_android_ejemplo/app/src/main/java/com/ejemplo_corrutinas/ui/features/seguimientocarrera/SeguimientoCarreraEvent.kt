package com.ejemplo_corrutinas.ui.features.seguimientocarrera

sealed interface SeguimientoCarreraEvent {
    data object OnEmpezarPararClick : SeguimientoCarreraEvent
    data object OnReiniciarClick : SeguimientoCarreraEvent
}