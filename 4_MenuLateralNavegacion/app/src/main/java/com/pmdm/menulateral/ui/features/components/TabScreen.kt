package com.pmdm.menulateral.ui.features.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun NavigationBarJoel(){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(com.pmdm.menulateral.R.drawable.groups_24px),
                    contentDescription = "People"
                )
            },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(com.pmdm.menulateral.R.drawable.factory_24px),
                    contentDescription = "Home"
                )
            },
            selected = false,
            onClick = {}
        )
}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun NavigationBarScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = { TopAppBarJoel(
            onSessionClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("Sesión cerrada")
                }
            },
            onCloseClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("Aplicación cerrada")
                }
            }
        ) },
        bottomBar = { NavigationBarJoel() }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Image(
                painter = painterResource(com.pmdm.menulateral.R.drawable.fabrica),
                contentDescription = "Fabrica",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            SecondaryScrollableTabRow(0) {
                Tab(
                    selected = true,
                    onClick = { /*TODO*/ },
                    text = {
                        Text("Actividad")
                    }
                )
                Tab(
                    selected = false,
                    onClick = { /*TODO*/ },
                    text = {
                        Text("Tareas")
                    }
                )
                Tab(
                    selected = false,
                    onClick = { /*TODO*/ },
                    text = {
                        Text("Contactos")
                    }

                )
            }
            Text("Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                    "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                    "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                    "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                    "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                    "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                    "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                    "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum",
                modifier = Modifier.padding(10.dp) )
        }

    }
}