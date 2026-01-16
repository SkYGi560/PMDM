package com.pmdm.navegacion.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.pmdm.navegacion.ui.features.PantallaScreen
import kotlinx.serialization.Serializable

@Serializable
data class Pantalla2Route(val pantallaAnterior: Int? = null)

fun NavGraphBuilder.pantalla2Destination(
    onNavigatePantallaAnterior: () -> Unit
) {
    composable<Pantalla2Route>{ backStackEntry ->
        PantallaScreen(
            pantalla = 2,
            pantallaDeDondeVengo = backStackEntry.toRoute<Pantalla1Route>().pantallaAnterior,
            onNavigatePantallaAnterior = onNavigatePantallaAnterior
        )
    }
}