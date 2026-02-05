package com.pmdm.navegacionlateral.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmdm.navegacionlateral.ui.features.ContactosScreen
import com.pmdm.navegacionlateral.ui.features.ListadoContactosViewModel
import com.pmdm.navegacionlateral.ui.theme.ProyectoBaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProyectoBaseTheme {
                val contactosViewModel: ListadoContactosViewModel = hiltViewModel()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContactosScreen(
                        listaContactos = contactosViewModel.lista,
                        itemSeleccionado = contactosViewModel.itemSeleccionado,
                        visualizarbottomBar = contactosViewModel.visualizarbottomBar,
                        onNavigateTo = contactosViewModel.onNavigateTo,
                        onItemSeleccionado = contactosViewModel.onItemSeleccionado
                    )
                }
            }
        }
    }
}
