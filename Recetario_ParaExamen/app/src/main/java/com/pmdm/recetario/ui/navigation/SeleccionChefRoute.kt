package com.pmdm.recetario.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.recetario.ui.features.FiltrosRecetas
import com.pmdm.recetario.ui.features.RecetarioViewModel
import com.pmdm.recetario.ui.features.seleccionchef.SeleccionChefDialog
import kotlinx.serialization.Serializable

@Serializable
object SeleccionChefRoute

fun NavGraphBuilder.seleccionChefDestination(
    vm: RecetarioViewModel,
    onCancelarSeleccionNombre: () -> Unit
) {
    composable<SeleccionChefRoute>{
        SeleccionChefDialog(
            listaChefsState = vm.listaChefs,
            onSeleccinarNombreChef = vm::onSeleccionaChef,
            onCancelarSeleccionNombre = onCancelarSeleccionNombre
        )
    }
}