package com.pmdm.scaffold.ui.features

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.icons.Filled
import com.pmdm.scaffold.R
import com.pmdm.scaffold.ui.theme.EjemplosScaffoldTheme

@Immutable
data class ItemMenuDesplegable(
    val icono: Int,
    val descripcion: String,
    val onClick: () -> Unit
)

@Composable
fun AccionesConMenuDesplegable(
    itemsMenu : List<ItemMenuDesplegable>
) {
    // Precondición de uso
    if (itemsMenu.count() < 3)
        throw IllegalArgumentException("Se requieren al menos 3 items en el menú desplegable")
    var expandidoState by remember { mutableStateOf(false) }
    val cerrarMenu: () -> Unit = { expandidoState = false }

    IconButton(onClick = itemsMenu[0].onClick) {
        Icon(
            painter = painterResource(itemsMenu[0].icono),
            contentDescription = itemsMenu[0].descripcion
        )
    }
    IconButton(onClick = { expandidoState = true }) {
        Icon(painter = painterResource(R.drawable.more_vert_24px), contentDescription = null)
    }

    DropdownMenu(
        expanded = expandidoState,
        onDismissRequest = cerrarMenu
    ) {
        for (i in 1..<itemsMenu.count()) {
            DropdownMenuItem(
                text = { Text(itemsMenu[i].descripcion) },
                onClick = {
                    itemsMenu[i].onClick
                    cerrarMenu()
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(itemsMenu[i].icono),
                        contentDescription = itemsMenu[i].descripcion
                    )
                })
        }
    }
}

@Composable
fun AccionesConMenuDesplegableSinSeleccion() {
    val descripcionEIconos = remember {
        listOf(
            ItemMenuDesplegable(
                icono = R.drawable.search_24px,
                descripcion = "Buscar Item", onClick = { }
            ),
            ItemMenuDesplegable(
                icono = R.drawable.filter_list_24px,
                descripcion = "Filtrar Item", onClick = { }
            ),
            ItemMenuDesplegable(
                icono = R.drawable.sort_by_alpha_24px,
                descripcion = "Ordenar Item", onClick = { }
            )
        )
    }
    return AccionesConMenuDesplegable(itemsMenu = descripcionEIconos)
}

@Composable
fun AccionesConMenuDesplegableSeleccion() {
    val descripcionEIconos = remember {
        listOf(
            ItemMenuDesplegable(
                icono = R.drawable.delete_24px,
                descripcion = "Eliminar Item",
                onClick = { }
            ),
            ItemMenuDesplegable(
                icono = R.drawable.favorite_24px,
                descripcion = "Completar Item",
                onClick = { }
            ),
            ItemMenuDesplegable(
                icono = R.drawable.download_24px,
                descripcion = "Descargar Item",
                onClick = { }
            ),
            ItemMenuDesplegable(
                icono = R.drawable.share_24px,
                descripcion = "Editar Item",
                onClick = { }
            )
        )
    }
    return AccionesConMenuDesplegable(
        itemsMenu = descripcionEIconos
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraAplicacionConDropDownMenu(
    itemSeleccionadoState: Boolean,
    comportamientoAnteScroll: TopAppBarScrollBehavior
) = TopAppBar(
    title = {
        Text("Ejemplo DropDown", maxLines = 1, overflow = TextOverflow.Ellipsis)
    },
    navigationIcon = {
        IconButton(onClick = { }) {
            Icon(painter = Filled.getArrowBackIosIcon(), contentDescription = null)
        }
    },
    actions = {
        if (itemSeleccionadoState) AccionesConMenuDesplegableSeleccion()
        else AccionesConMenuDesplegableSinSeleccion()
    },
    scrollBehavior = comportamientoAnteScroll
)

@Composable
fun ContenidoPrincipalEjemploDropDownMenu(
    itemSeleccionadoState: Int?,
    onSeleccionarItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(count = 25) {
            Surface(
                color = if (itemSeleccionadoState == it) {
                    MaterialTheme.colorScheme.secondary
                } else {
                    MaterialTheme.colorScheme.secondaryContainer
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSeleccionarItem(it) }
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                    )
            ) {
                Text(
                    text = "Item $it",
                    modifier = Modifier.padding(16.dp),
                    fontWeight = if (itemSeleccionadoState == it) {
                        FontWeight.ExtraBold
                    } else {
                        FontWeight.Normal
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAppBarConDropDownMenu() {
    val comportamientoAnteScroll = TopAppBarDefaults.pinnedScrollBehavior()
    var itemSeleccionadoState: Int? by remember { mutableStateOf(null) }
    val onSeleccionarItem: (Int) -> Unit = {
        itemSeleccionadoState = if (itemSeleccionadoState != it) it else null
    }
    Scaffold(
        modifier = Modifier.nestedScroll(comportamientoAnteScroll.nestedScrollConnection),
        topBar = {
            BarraAplicacionConDropDownMenu(
                itemSeleccionadoState = itemSeleccionadoState != null,
                comportamientoAnteScroll = comportamientoAnteScroll
            )
        },
        content = { innerPadding ->
            ContenidoPrincipalEjemploDropDownMenu(
                itemSeleccionadoState = itemSeleccionadoState,
                onSeleccionarItem = onSeleccionarItem,
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PantallaAppBarConDropDownMenuPreview() {
    EjemplosScaffoldTheme {
        Surface {
            PantallaAppBarConDropDownMenu()
        }
    }
}