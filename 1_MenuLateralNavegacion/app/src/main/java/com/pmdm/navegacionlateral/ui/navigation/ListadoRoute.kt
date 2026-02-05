package com.pmdm.navegacionlateral.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.navegacionlateral.models.Contacto
import com.pmdm.navegacionlateral.ui.features.ListadoScreen
import kotlinx.serialization.Serializable

@Serializable
object ListadoRoute

fun NavGraphBuilder.listadoDestination(
    paddingValues: PaddingValues,
    lista: List<Contacto>,
    onNavigateTo: (Int) -> Unit
) {
    composable<ListadoRoute> {
        ListadoScreen(
            modifier = Modifier.padding(paddingValues),
            listaContactos = lista,
            onNavigateTo = onNavigateTo
        )
    }
}
