package com.pmdm.tienda.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier


import com.pmdm.tienda.ui.theme.LoginTheme
import com.pmdm.tienda.ui.features.login.LoginScreen
import com.pmdm.tienda.ui.features.login.LoginViewModel
import com.pmdm.tienda.ui.features.newuser.NewUserScreen
import com.pmdm.tienda.ui.features.newuser.NewUserViewModel

import com.pmdm.tienda.ui.features.tienda.TiendaScreen
import com.pmdm.tienda.ui.features.tienda.TiendaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginViewModel: LoginViewModel by viewModels()
        val newUserViewModel: NewUserViewModel by viewModels()

        val tiendaViewModel: TiendaViewModel by viewModels()

        setContent {
            LoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    /*      LoginScreen(
                              usuarioUiState = loginViewModel.usuarioUiState,
                              validacionLoginUiState = loginViewModel.validacionLoginUiState,
                              onLoginEvent = loginViewModel::onLoginEvent,
                          )*/
                  /*  NewUserScreen(
                        newUserUiState = newUserViewModel.newUserUiState,
                        validacionNewUserUiState = newUserViewModel.validacionNewUserUiState,
                        mostrarSnack = newUserViewModel.mostrarSnackState,
                        mensaje = newUserViewModel.mensajeSnackBarState,
                        incrementaPagina = newUserViewModel.incrementaPaginaState,
                        onDireccionEvent = newUserViewModel::onDireccionEvent,
                        onDatosPersonalesEvent = newUserViewModel::onDatosPersonalesEvent,
                        onNewUserPasswordEvent = newUserViewModel::onNewUserPasswordEvent
                    )*/
                    TiendaScreen(
                            clienteUiState = tiendaViewModel.clienteState,
                            articulos = tiendaViewModel.articulosState,
                            articuloSeleccionado = tiendaViewModel.articuloSeleccionadoState,
                            filtro = tiendaViewModel.filtroState,
                            estaFiltrando = tiendaViewModel.estaFiltrandoState,
                            carrito = tiendaViewModel.carritoState,
                            numerArticulos = tiendaViewModel.numeroArticulosState,
                            totalCompra = tiendaViewModel.totalCompraState,
                            pedido = tiendaViewModel.pedidoUiState,
                            tallaUiState = tiendaViewModel.tallaUiState,
                            onTallaEvent = tiendaViewModel::onTallaEvent ,
                            onTiendaEvent = tiendaViewModel::onTiendaEvent
                        )
                }
            }
        }
    }
}
