package com.pmdm.navegacion.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.pmdm.navegacion.ui.features.PantallaScreen
import kotlinx.serialization.Serializable

@Serializable
data class Pantalla3Route(val pantallaAnterior: Int? = null)

fun NavGraphBuilder.pantalla3Destination(
    onNavigatePantallaAnterior: () -> Unit
) {
    composable<Pantalla3Route>{ backStackEntry ->
        PantallaScreen(
            pantalla = 3,
            pantallaDeDondeVengo = backStackEntry.toRoute<Pantalla1Route>().pantallaAnterior,
            onNavigatePantallaAnterior = onNavigatePantallaAnterior
        )
    }
}