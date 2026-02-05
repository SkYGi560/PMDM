package com.pmdm.ejemploroom.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmdm.ejemploroom.ui.features.InformacionScreen
import com.pmdm.ejemploroom.ui.features.InformacionViewModel
import com.pmdm.ejemploroom.ui.theme.EjemploRoom
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemploRoom {
                val viewModel:InformacionViewModel = hiltViewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  InformacionScreen(modifier = Modifier.padding(innerPadding),  viewModel.clienteConPedidoUiState)
                }
            }
        }
    }
}

