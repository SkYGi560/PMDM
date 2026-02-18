package com.pmdm.recetario.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.recetario.ui.features.listarecetas.RecetarioScreen
import com.pmdm.recetario.ui.features.RecetarioViewModel
import kotlinx.serialization.Serializable

@Serializable
object ListadoRecetasRoute

fun NavGraphBuilder.listaRecetasDestination(
    vm: RecetarioViewModel,
    onNavigateToDialogChefs: () -> Unit,
    onNavigateToDetalleReceta: () -> Unit
) {
    composable<ListadoRecetasRoute>{
        RecetarioScreen(
            recetaSleccionadaState = vm.recetaSeleccionadaState,
            listaRecetasState = vm.listaRecetasState,
            filtroState = vm.filtroState,
            onRecetarioEvent = vm::onRecetarioEvent,
            onNavigateToDialogChefs = {onNavigateToDialogChefs()},
            onNavigateToDetalleReceta = onNavigateToDetalleReceta
        )
    }
}