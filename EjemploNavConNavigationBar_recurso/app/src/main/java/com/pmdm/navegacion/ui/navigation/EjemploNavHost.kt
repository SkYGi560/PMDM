package com.pmdm.navegacion.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun EjemploNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Pantalla1Route()
    ) {
        val onNavigatePantallaAnterior: () -> Unit = {
            navController.popBackStack()
        }
        pantalla1Destination(onNavigatePantallaAnterior = onNavigatePantallaAnterior)
        pantalla2Destination(onNavigatePantallaAnterior = onNavigatePantallaAnterior)
        pantalla3Destination(onNavigatePantallaAnterior = onNavigatePantallaAnterior)
    }
}