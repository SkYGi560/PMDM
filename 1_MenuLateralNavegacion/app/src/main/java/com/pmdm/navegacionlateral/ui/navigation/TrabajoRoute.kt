package com.pmdm.navegacionlateral.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.pmdm.navegacionlateral.ui.features.TrabajoScreen
import com.pmdm.navegacionlateral.ui.features.TrabajoViewModel
import kotlinx.serialization.Serializable

@Serializable
data class TrabajoRoute(val idContacto: Int)

fun NavGraphBuilder.trabajoDestination(
    vm: TrabajoViewModel,
    paddingValues: PaddingValues
) {
    composable<TrabajoRoute> { backStackEntry ->
        val contacto = vm.getContacto(backStackEntry.toRoute<TrabajoRoute>().idContacto)
        if (contacto != null) {
            TrabajoScreen(
                modifier = Modifier.padding(paddingValues),
                contacto = contacto,
                selectedTabIndex = vm.selectedTabIndex,
                onSelectedTabIndex = vm.onSelectedTabIndex
            )
        }
    }
}