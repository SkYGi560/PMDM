package com.pmdm.navegacionlateral.ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.navegacionlateral.R
import com.pmdm.navegacionlateral.models.Contacto
import com.pmdm.navegacionlateral.models.TipoContacto


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrabajoScreen(
    modifier: Modifier = Modifier,
    contacto: Contacto,
    selectedTabIndex: Int = 0,
    onSelectedTabIndex: (Int) -> Unit = {}
) {

    Column(modifier = modifier) {
        Box()
        {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.fabrica),
                contentDescription = "Fabrica",
                contentScale = ContentScale.Crop
            )
            Text(
                text = contacto.nombre,
                modifier = Modifier.align(Alignment.TopStart),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }
        val listaTitulosTab = listOf("Sobre Mi", "Publicaciones", "Horario")

        SecondaryScrollableTabRow(
            selectedTabIndex = selectedTabIndex, scrollState = ScrollState(selectedTabIndex)
        ) {

            repeat(3) { posicion ->
                Tab(selected = selectedTabIndex == posicion,
                    onClick = { onSelectedTabIndex(posicion) },
                    text = { Text(text = listaTitulosTab[posicion]) })
            }
        }

        Box(modifier = Modifier.padding(20.dp)) {
           when(selectedTabIndex) {
               0 ->  Text(text = "DATOS PERSONALES", style = MaterialTheme.typography.titleLarge)
               1 -> Text(text = "Lorem ipsum dolor sit amet consectetur adipiscing elit imperdiet suscipit blandit donec cursus parturient sollicitudin nisl lacus praesent, mauris fringilla rhoncus non inceptos risus in vitae pellentesque cras suspendisse lectus faucibus tristique duis. Nulla rhoncus nec facilisi leo eros senectus metus, magnis sollicitudin tellus commodo elementum mattis congue tempor, velit ut vel ornare magna vulputate. Sollicitudin eros quam natoque urna sapien vel aptent massa lobortis posuere, ac cursus sem cras himenaeos luctus neque fames aliquam interdum, non per enim consequat sagittis fermentum rhoncus a purus.")
               else ->   Text(text = "HORARIO", style = MaterialTheme.typography.titleLarge)           }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TrabajoScreenTest() {
    val contacto = Contacto(1, "Pepe", TipoContacto.Trabajo)
    TrabajoScreen(contacto = contacto, selectedTabIndex = 0, onSelectedTabIndex = {})
}



