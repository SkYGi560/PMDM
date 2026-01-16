package com.pmdm.navegacion.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.pmdm.navegacion.ui.features.PantallaScreen
import kotlinx.serialization.Serializable

@Serializable
data class Pantalla1Route(val pantallaAnterior: Int? = null)

fun NavGraphBuilder.pantalla1Destination(
    onNavigatePantallaAnterior: () -> Unit
) {
    composable<Pantalla1Route>{ backStackEntry ->
        PantallaScreen(
            pantalla = 1,
            pantallaDeDondeVengo = backStackEntry.toRoute<Pantalla1Route>().pantallaAnterior,
            onNavigatePantallaAnterior = onNavigatePantallaAnterior
        )
    }
}