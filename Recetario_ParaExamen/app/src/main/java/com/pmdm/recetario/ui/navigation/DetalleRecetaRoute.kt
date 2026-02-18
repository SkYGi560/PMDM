package com.pmdm.recetario.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.recetario.ui.features.fichareceta.DetalleRecetaScreen
import com.pmdm.recetario.ui.features.RecetarioViewModel
import kotlinx.serialization.Serializable

@Serializable
object DetalleRecetaRoute

fun NavGraphBuilder.detalleRecetaDestination(
    vm: RecetarioViewModel,
    onVolver: () -> Unit
) {
    composable<DetalleRecetaRoute>{
        DetalleRecetaScreen(
            recetaState = vm.recetaSeleccionadaState!!,
            onFavoritaClick = vm::onFavoritaClick,
            onVolverClick = onVolver
        )
    }
}