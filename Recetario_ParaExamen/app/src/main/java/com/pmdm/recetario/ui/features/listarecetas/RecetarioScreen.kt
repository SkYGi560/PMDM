package com.pmdm.recetario.ui.features.listarecetas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditScore
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterListOff
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.InsertLink
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.pmdm.recetario.data.aReceta
import com.pmdm.recetario.data.mocks.RecetaDaoMock
import com.pmdm.recetario.model.Receta
import com.pmdm.recetario.ui.features.FiltrosRecetas
import com.pmdm.recetario.ui.features.RecetarioEvent
import com.pmdm.recetario.ui.theme.RecetarioTheme
import kotlin.math.exp

private fun iconoFiltroPorTipo(filtro: FiltrosRecetas): ImageVector =
    when (filtro) {
        FiltrosRecetas.Todas -> Icons.Default.FilterListOff
        FiltrosRecetas.PorChef -> Icons.Default.Restaurant
        FiltrosRecetas.Favoritas -> Icons.Default.Favorite
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecetarioScreen(
    recetaSleccionadaState: Receta?,
    listaRecetasState: List<Receta>,
    filtroState: FiltrosRecetas,
    onRecetarioEvent: (RecetarioEvent) -> Unit,
    onNavigateToDialogChefs: () -> Unit,
    onNavigateToDetalleReceta: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val mapaFiltros = mutableMapOf<FiltrosRecetas, ImageVector>().apply {
        put(FiltrosRecetas.Todas, Icons.Default.FilterListOff)
        put(FiltrosRecetas.PorChef, Icons.Default.Restaurant)
        put(FiltrosRecetas.Favoritas, Icons.Default.Favorite)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recetario") },
                actions = {
                    Text(filtroState.texto)
                    IconButton(
                        onClick = {expanded = true}
                    ){
                        Icon(imageVector = mapaFiltros.getValue(filtroState), contentDescription = "IconoFiltros")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {expanded = false}
                    ){
                        mapaFiltros.forEach { filtro ->
                            DropdownMenuItem(
                                text = {Text(filtro.key.texto)},
                                onClick = {

                                    if(filtro.key == FiltrosRecetas.PorChef){
                                        onNavigateToDialogChefs
                                    } else
                                        onRecetarioEvent(RecetarioEvent.OnFiltroClick(filtro.key))
                                          },
                                leadingIcon = {Icon(imageVector = filtro.value,contentDescription = "contnt")}
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            if(recetaSleccionadaState != null){
                BottomAppBar(
                    actions = {
                        IconButton(
                            onClick = onNavigateToDetalleReceta
                        ) {
                            Icon(imageVector = Icons.Filled.Info, contentDescription = "Info")
                        }
                        IconButton(
                            onClick = {onRecetarioEvent(RecetarioEvent.OnMarcarFavoritaClick)}
                        ) {
                            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favoritos")
                        }
                        IconButton(
                            onClick = {onRecetarioEvent(RecetarioEvent.OnBorrarClick)}
                        ) {
                            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
                        }
                    }
                )
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(listaRecetasState){ receta ->
                ItemReceta(
                    receta = receta,
                    onClick = {onRecetarioEvent(RecetarioEvent.OnSeleccionarReceta(receta.id))},
                    seleccionado = recetaSleccionadaState?.id == receta.id
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RecetarioScreenPreview() {
    var recetaSleccionadaState: Receta? by remember { mutableStateOf(null) }
    var listaRecetasState by remember { mutableStateOf(emptyList<Receta>()) }
    LaunchedEffect(true) {
        listaRecetasState = RecetaDaoMock().getTodas().map { it.aReceta() }
    }

    var filtroState by remember { mutableStateOf(FiltrosRecetas.Todas) }

    RecetarioTheme {
        Surface {
            RecetarioScreen(
                recetaSleccionadaState = recetaSleccionadaState,
                listaRecetasState = listaRecetasState,
                filtroState =  filtroState,
                onRecetarioEvent = {},
                onNavigateToDialogChefs = {},
                onNavigateToDetalleReceta = {}
            )
        }
    }
}