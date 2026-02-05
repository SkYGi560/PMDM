package com.pmdm.navegacionlateral.ui.features

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pmdm.navegacionlateral.R
import com.pmdm.navegacionlateral.models.Contacto
import com.pmdm.navegacionlateral.models.TipoContacto
import com.pmdm.navegacionlateral.ui.features.components.BottomBar
import com.pmdm.navegacionlateral.ui.features.components.TopBar
import com.pmdm.navegacionlateral.ui.navigation.ContactosNavHost
import com.pmdm.navegacionlateral.ui.navigation.ListadoRoute
import kotlinx.coroutines.launch


@Composable
fun ContactosScreen(
    listaContactos: List<Contacto>,
    itemSeleccionado: Int,
    visualizarbottomBar: Boolean,
    onNavigateTo: (TipoContacto) -> Unit,
    onItemSeleccionado: (Int) -> Unit,
    navController :NavHostController= rememberNavController()
) {
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val listaMenu = listOf("Todos", "Amigos", "Coro", "Familia", "Trabajo", "Vecinos")
    val scope = rememberCoroutineScope()


    val onClickMenu: () -> Unit = {
        scope.launch {
            if (drawerState.isClosed) drawerState.open() else drawerState.close()
        }
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ContenedorDrawer(
                listaMenu,
                itemSeleccionado
            ) {
                onItemSeleccionado(it)
                scope.launch { drawerState.close() }
                navController.navigate(ListadoRoute)
            }
        },
        content = {
            ContenidoPrincipal(
                listaContactos = listaContactos,
                visualizarbottomBar=visualizarbottomBar,
                onNavigateTo=onNavigateTo,
                onClickMenu = onClickMenu,
                navController=navController
            )
        })
}


@Composable
fun ContenedorDrawer(
    listaMenu: List<String>,
    itemSeleccionado: Int,
    onItemSeleccionado: (Int) -> Unit
) {
    ModalDrawerSheet {

        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.contactos),
            contentDescription = "Fabrica",
            contentScale = ContentScale.Crop
        )

        for (i in 0 until listaMenu.size) {
            if (i == 1) HorizontalDivider()
            NavigationDrawerItem(
                label = { Text(listaMenu[i]) },
                selected = i == itemSeleccionado,
                onClick = {
                    onItemSeleccionado(i)
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContenidoPrincipal(
    listaContactos: List<Contacto>,
    visualizarbottomBar: Boolean,
    onNavigateTo: (TipoContacto) -> Unit,
    onClickMenu: () -> Unit,
    navController :NavHostController
) {
    val activity = LocalContext.current as? Activity
    val comportamientoScroll = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.nestedScroll(comportamientoScroll.nestedScrollConnection),
        snackbarHost= { SnackbarHost(snackbarHostState) },
        topBar = { TopBar(comportamientoScroll, snackbarHostState, onClickMenu)
            {
                activity?.finish()
            } },
        bottomBar = { if (visualizarbottomBar) BottomBar() },
        content = { paddingValues ->
            ContactosNavHost(
                paddingValues,
                listaContactos,
                navController,
                onNavigateTo
            )
        })
}


@Composable
@Preview(showBackground = true)
fun ContactosScreenTest() {

}