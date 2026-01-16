package com.pmdm.scaffold.features.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarJoel(
    onSessionClick: () -> Unit = {},
    onCloseClick: () -> Unit = {},
    icono1: ImageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft ,
    icono2: ImageVector = Icons.Filled.Settings,
    icono3: ImageVector = Icons.Filled.Menu
){
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text("")
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = icono1,
                    contentDescription = "Flecha izq"
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(icono2, contentDescription = "Settings")
            }
            IconButton(onClick = { expanded = true }) {
                Icon(icono3, contentDescription = "Menu")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Cerrar Sesión") },
                    onClick = { onSessionClick }
                )
                DropdownMenuItem(
                    text = { Text("Salir Aplicación") },
                    onClick = { onCloseClick }
                )
            }
        },
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarScreen(){
    Scaffold(
        topBar = {
            TopAppBarJoel()
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {}
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        },
        modifier = Modifier.fillMaxSize(),

    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(20){ i ->
                    ElevatedCard(
                        Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                    ) {
                        Text(
                            text = "Contacto ${i+1}",
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally))
                    }
                }
            }
        }
    }
}