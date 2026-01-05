package com.pmdm.agenda.ui.views

import com.pmdm.agenda.features.formcontacto.ContactoViewModel
import com.pmdm.agenda.features.formcontacto.FormContactoScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pmdm.agenda.ui.theme.AgendaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm : ContactoViewModel by viewModels() // Creamos el ViewModel
        // Le indicamos que queremos editar un contacto existente con id 1
        vm.setContactoState(1)
        setContent {
            AgendaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormContactoScreen(
                        contactoState = vm.contactoState,
                        validacionContactoState = vm.validacionContactoState,
                        onContactoEvent = vm::onContactoEvent,
                        onNavigateTrasFormContacto = {}
                    )
                }
            }
        }
    }
}