package com.pmdm.tienda.ui.features.tienda.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.pmdm.tienda.ui.features.tienda.TiendaEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarTienda(
    estaFiltrando: Boolean,
    onTiendaEvent: (TiendaEvent) -> Unit
){
    var searchBarExpanded by remember { mutableStateOf(false) }
    var dropDownMenuExpanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {},
        actions = {
                IconButton(onClick = {
                    onTiendaEvent(TiendaEvent.OnClickEstaFiltrando(estaFiltrando))
                    searchBarExpanded = true
                }) {
                    Icon(Icons.Filled.Search, contentDescription = null)
                }
                SearchBar(
                    inputField = {
                        TextField(
                            value = "",
                            onValueChange = {},
                            placeholder = { Text("Filtrar") },
                            singleLine = true
                        )
                    },
                    expanded = searchBarExpanded,
                    onExpandedChange = { searchBarExpanded = it }
                ) {}

            IconButton(onClick = { dropDownMenuExpanded = true }) {
                Column {
                    Icon(Icons.Filled.Person, contentDescription = null)
                    Text("Page")
                }
            }
            DropdownMenu(
                expanded = dropDownMenuExpanded,
                onDismissRequest = { dropDownMenuExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Editar datos") },
                    onClick = {  }
                )
                DropdownMenuItem(
                    text = { Text("Cerrar sesión") },
                    onClick = {  }
                )
            }

        }
    )
}