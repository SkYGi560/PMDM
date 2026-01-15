package com.pmdm.navegacionlateral.ui.features.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    comportamientoScroll: TopAppBarScrollBehavior,
    snackbarHostState: SnackbarHostState,
    onClickMenu: () -> Unit,
    onClickSalir:()-> Unit
) {
    var mostrarMenu by remember {
        mutableStateOf(false)
    }
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.inversePrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.inversePrimary
        ),
        title = { },
        navigationIcon = {
            Icon(Icons.Filled.Menu, "Volver", modifier = Modifier.clickable { onClickMenu() })
        },
        actions = {
            Icon(Icons.Filled.Settings, "Configuración", modifier = Modifier.clickable {})
            Icon(
                Icons.Filled.MoreVert,
                "Menú",
                modifier = Modifier.clickable { mostrarMenu = !mostrarMenu })
            MenuDesplegable(mostrarMenu, snackbarHostState, { mostrarMenu = it }, onClickSalir)
        },
        scrollBehavior = comportamientoScroll

    )

}
@Composable
fun BottomBar() {
    BottomAppBar(

        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.Edit, contentDescription = "")

            }
            IconButton(onClick = { /* Delete onClick */ }) {
                Icon(Icons.Filled.Delete, contentDescription = "")
            }
        },
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.inversePrimary,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = MaterialTheme.colorScheme.inversePrimary,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(Icons.Filled.Add, "Añadir")
            }
        })
}


@Composable
fun MenuDesplegable(
    mostrarMenu: Boolean,
    snackbarHostState: SnackbarHostState,
    onMostrarMenu: (Boolean) -> Unit,
    onClickSalir:()-> Unit
) {
    val scope = rememberCoroutineScope()
    DropdownMenu(expanded = mostrarMenu,
        onDismissRequest = { onMostrarMenu(false) }) {
        DropdownMenuItem(text = { Text("Cerrar Sesión") }, onClick = {
            scope.launch {
                MostrarSnackBar(
                    snackbarHostState = snackbarHostState, "Se está cerrando la sesión"
                )
            }
            onMostrarMenu(false)
        })
        DropdownMenuItem(text = { Text("Salir Aplicación") }, onClick = {
            scope.launch {
                MostrarSnackBar(
                    snackbarHostState = snackbarHostState, "Saliendo de la aplicación"
                )
            }
            onMostrarMenu(false)
            onClickSalir()
        })
    }
}

suspend fun MostrarSnackBar(snackbarHostState: SnackbarHostState, mensaje: String) {
    snackbarHostState.currentSnackbarData?.dismiss()
    snackbarHostState.showSnackbar(
        message = mensaje,
    )
}

