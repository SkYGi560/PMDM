package com.pmdm.ejemplotextfielviewmodel.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.pmdm.ejemplotextfielviewmodel.ui.features.DatosClienteScreen
import com.pmdm.ejemplotextfielviewmodel.ui.features.DatosClienteViewModel
import com.pmdm.ejemplotextfielviewmodel.ui.theme.EjemploTextFielViewModelTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val datosClienteViewModel: DatosClienteViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            EjemploTextFielViewModelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DatosClienteScreen(
                        modifier = Modifier.padding(innerPadding),
                        datosClienteUiState = datosClienteViewModel.datosClienteUiState,
                        datosClienteValidacion = datosClienteViewModel.validacionDatosClienteUiState
                        ,onDatosClienteEvent = datosClienteViewModel::onDatosClienteEvent
                    )


                }


            }
        }
    }
}

