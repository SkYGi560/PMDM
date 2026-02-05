package com.pmdm.navegacionlateral.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pmdm.navegacionlateral.models.Contacto
import com.pmdm.navegacionlateral.models.TipoContacto
import com.pmdm.navegacionlateral.ui.features.TrabajoViewModel

@Composable
fun ContactosNavHost(
    paddingValues: PaddingValues,
    listaContactos: List<Contacto>,
    navController: NavHostController,
    onNavigateTo: (TipoContacto) -> Unit
) {
    var trabjoViewModel = hiltViewModel<TrabajoViewModel>()
    NavHost(
        navController = navController,
        startDestination = ListadoRoute
    ) {
        listadoDestination(paddingValues = paddingValues, listaContactos) { idContacto ->
            val contacto = listaContactos.find { it.id == idContacto }
            if (contacto != null) {
                onNavigateTo(contacto.tipo)
                if (contacto.tipo == TipoContacto.Trabajo) {
                    navController.navigate(TrabajoRoute(idContacto))
                }
            }
        }
        trabajoDestination(
            vm = trabjoViewModel,
            paddingValues = paddingValues
        )
    }

}

