package com.pmdm.agenda.features.vercontactos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.utilities.imagetools.base64ToImageBitmap
import com.pmdm.agenda.data.mocks.contacto.ContactoDaoMock
import com.pmdm.agenda.features.ContactoUiState


@Composable
fun ListaContactosScreen(
    modifier: Modifier = Modifier,
    contactosState: List<ContactoUiState>,
    contactoSeleccionadoState: ContactoUiState?,
    onContactoClicked: (ContactoUiState) -> Unit,
    onAddClicked: () -> Unit = {},
    onEditClicked: () -> Unit = {},
    onDeleteClicked: () -> Unit = {}
) {
    Box(modifier = modifier.then(Modifier.fillMaxSize())) {
        LazyColumn(
            contentPadding = PaddingValues(all = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                contactosState,
                key = { it.id }
            ) { contacto ->
                ContactoListItem(
                    modifier = Modifier.animateItem(),
                    contactoUiState = contacto,
                    seleccionadoState = contactoSeleccionadoState
                        ?.let { it.id == contacto.id } ?: false,
                    onContactoClicked = onContactoClicked,
                    onEditClicked = onEditClicked,
                    onDeleteClicked = onDeleteClicked
                )
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            onClick = onAddClicked
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Crear Contacto")
        }
    }
}
@Preview(device = "id:pixel_3a", showBackground = true)
@Composable
fun ListaContactosScreenTest() {
    val contactos by remember {
        mutableStateOf(ContactoDaoMock().get().map {
            ContactoUiState(
                id = it.id,
                foto = it.foto?.base64ToImageBitmap(),
                nombre = it.nombre,
                apellidos = it.apellidos,
                telefono = it.telefono,
                correo = it.correo,
            )
        })
    }

// Definimos un estado que nos permitirá gestionar el contacto
// actualmente seleccionado y su manejador
    var contactoSeleccionadoState: ContactoUiState? by remember { mutableStateOf(null) }
    val onContactoClicked: (ContactoUiState) -> Unit = { c ->
        contactoSeleccionadoState =
            if (contactoSeleccionadoState == null
                || c.id != contactoSeleccionadoState!!.id
            ) c.copy()
            else null
    }

    ListaContactosScreen(
        modifier = Modifier.fillMaxSize(),
        contactosState = contactos,
        contactoSeleccionadoState = contactoSeleccionadoState,
        onContactoClicked = onContactoClicked,
        onAddClicked = {},
        onEditClicked = {},
        onDeleteClicked = {}
    )
}