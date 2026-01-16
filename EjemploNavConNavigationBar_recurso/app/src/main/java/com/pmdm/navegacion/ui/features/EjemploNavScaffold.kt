package com.pmdm.navegacion.ui.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.pmdmiesbalmis.components.ui.icons.Filled
import com.pmdm.navegacion.R
import com.pmdm.navegacion.ui.navigation.EjemploNavHost
import com.pmdm.navegacion.ui.navigation.Pantalla1Route
import com.pmdm.navegacion.ui.navigation.Pantalla2Route
import com.pmdm.navegacion.ui.navigation.Pantalla3Route
import com.pmdm.navegacion.ui.theme.EjemploNavegacionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarEnEjemploNav(
    comportamientoAnteScroll: TopAppBarScrollBehavior,
    onNavegarAtras: () -> Unit,
    onDeshacerNavegacion: () -> Unit
) = TopAppBar(
    title = {
        Text("Ejemplo NavigationBar", maxLines = 1, overflow = TextOverflow.Ellipsis)
    },
    navigationIcon = {
        IconButton(onClick = onNavegarAtras) {
            Icon(painter = Filled.getArrowBackIosIcon(), contentDescription = null)
        }
    },
    actions = {
        IconButton(onClick = onDeshacerNavegacion) {
            Icon(painter = painterResource(R.drawable.home_24px), contentDescription = null)
        }
    },
    scrollBehavior = comportamientoAnteScroll
)

@Composable
fun ContenidoPrincipalEnEjemploNav(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) { EjemploNavHost(navController) }
}

@Composable
fun NavigationBarEnEjemploNav(
    iOpcionNevagacionSeleccionada: Int = 0,
    onNavigateToScreen: (Int) -> Unit
) {
    val titlesAndIcons = remember {
        listOf(
            "Pantalla 1" to R.drawable.filter_1_24px,
            "Pantalla 2" to R.drawable.filter_2_24px,
            "Pantalla 3" to R.drawable.filter_3_24px
        )
    }

    NavigationBar {
        titlesAndIcons.forEachIndexed { index, (title, idIcon) ->
            NavigationBarItem(
                icon = {
                    Icon(painter = painterResource(idIcon), contentDescription = title)
                },
                label = { Text(title) },
                selected = iOpcionNevagacionSeleccionada == index,
                onClick = { onNavigateToScreen(index) }
            )
        }
    }
}

private fun iOpcionNevagacionSeleccionadaAPartirDeDestino(destino: NavDestination?): Int {
    return when {
        destino == null -> 0
        destino.hasRoute<Pantalla1Route>() -> 0
        destino.hasRoute<Pantalla2Route>() -> 1
        destino.hasRoute<Pantalla3Route>() -> 2
        else -> 0
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EjemploNavDentroDeUnScaffold() {
    val comportamientoAnteScroll = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val navController = rememberNavController()
    val entradaEnPilaDeNavegacionActuasState by navController.currentBackStackEntryAsState()
    val iOpcionNevagacionSeleccionada by remember {
        derivedStateOf {
            iOpcionNevagacionSeleccionadaAPartirDeDestino(
                entradaEnPilaDeNavegacionActuasState?.destination
            )
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(comportamientoAnteScroll.nestedScrollConnection),
        topBar = {
            TopAppBarEnEjemploNav(
                comportamientoAnteScroll = comportamientoAnteScroll,
                onNavegarAtras = {
                    navController.popBackStack()
                },
                onDeshacerNavegacion = {
                    navController.popBackStack(navController.graph.startDestinationRoute!!, false)
                })
        },
        bottomBar = {
            NavigationBarEnEjemploNav(
                iOpcionNevagacionSeleccionada = iOpcionNevagacionSeleccionada,
                onNavigateToScreen = { i ->
                    when (i) {
                        0 -> navController.navigate(
                            Pantalla1Route(pantallaAnterior = iOpcionNevagacionSeleccionada + 1)
                        )
                        1 -> navController.navigate(
                            Pantalla2Route(pantallaAnterior = iOpcionNevagacionSeleccionada + 1)
                        )

                        2 -> navController.navigate(
                            Pantalla3Route(pantallaAnterior = iOpcionNevagacionSeleccionada + 1)
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            ContenidoPrincipalEnEjemploNav(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PantallaNavBarPreview() {
    EjemploNavegacionTheme {
        Surface {
            EjemploNavDentroDeUnScaffold()
        }
    }
}