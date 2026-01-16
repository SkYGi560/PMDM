package com.pmdm.agenda.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmdm.agenda.ui.features.formcontacto.ContactoViewModel
import com.pmdm.agenda.ui.features.formcontacto.FormContactoScreen
import com.pmdm.agenda.ui.features.vercontactos.ItemListaContactosEvent
import com.pmdm.agenda.ui.features.vercontactos.ListaContactosScreen
import com.pmdm.agenda.ui.features.vercontactos.ListaContactosViewModel
import com.pmdm.agenda.ui.theme.AgendaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm : ContactoViewModel by viewModels()
        vm.setContactoState(1)

        setContent {
            AgendaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VerListaContactos()
                }
            }
        }
    }
}

@Composable
fun VerFormContacto(vm : ContactoViewModel = hiltViewModel() ) {
    FormContactoScreen(
        contactoState = vm.contactoState,
        validacionContactoState = vm.validacionContactoState,
        informacionEstado = vm.informacionEstadoState,
        onContactoEvent = vm::onContactoEvent,
        onNavigateTrasFormContacto = {}
    )
}

@Composable
fun VerListaContactos(vm : ListaContactosViewModel = hiltViewModel()) {
    ListaContactosScreen(
        contactosState = vm.contactosState,
        contactoSeleccionadoState = vm.contatoSleccionadoState,
        filtradoActivoState = vm.filtradoActivoState,
        filtroCategoriaState = vm.filtroCategoriaState,
        informacionEstadoState = vm.informacionEstadoState,
        onActualizaContactos =  { vm.cargaContactos() },
        onActivarFiltradoClicked = { vm.onActivarFiltradoClicked() },
        onFiltroModificado = { categorias -> vm.onFiltroModificado(categorias) },
        onContactoClicked = { c ->
            vm.onItemListaContatoEvent(ItemListaContactosEvent.OnClickContacto(c))
        },
        onAddClicked = {},
        onEditClicked = {},
        onDeleteClicked = {
            vm.onItemListaContatoEvent(ItemListaContactosEvent.OnDeleteContacto)
        }
    )
}