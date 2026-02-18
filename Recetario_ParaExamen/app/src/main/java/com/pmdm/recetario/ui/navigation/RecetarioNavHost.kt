package com.pmdm.recetario.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pmdm.recetario.ui.features.RecetarioViewModel

@Composable
fun RecetarioNavHost() {
    val navController = rememberNavController()
    val vm = hiltViewModel<RecetarioViewModel>()

    NavHost(
        navController = navController,
        startDestination = ListadoRecetasRoute
    ) {
        listaRecetasDestination(
            vm = vm,
            onNavigateToDialogChefs = { navController.navigate(SeleccionChefRoute) },
            onNavigateToDetalleReceta = { navController.navigate(DetalleRecetaRoute) }
        )
        detalleRecetaDestination(
            vm = vm,
            onVolver = {
                navController.popBackStack()
            }
        )
        seleccionChefDestination(
            vm = vm,
            onCancelarSeleccionNombre = {navController.popBackStack()}
        )
    }
}
