package com.pmdm.scaffold.ui.features

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.scaffold.ui.theme.EjemplosScaffoldTheme

@Composable
fun ScaffoldFrame(
    modifier: Modifier = Modifier,
    contenido: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier.then(
            Modifier
                .fillMaxSize().padding(2.dp)
                .border(2.dp, MaterialTheme.colorScheme.inverseSurface)
                .clip(RoundedCornerShape(10.dp)))
    ) {
        contenido()
    }
}

@Composable
fun MiScaffold(
    cabecera: @Composable () -> Unit = {},
    menu: @Composable () -> Unit = {},
    cuerpo: @Composable () -> Unit = {},
    pie: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScaffoldFrame(modifier = Modifier.weight(1f)) { cabecera() }
        Row(modifier = Modifier
            .weight(10f)
            .fillMaxSize()
        ) {
            ScaffoldFrame(modifier = Modifier.weight(2f)) { menu() }
            ScaffoldFrame(modifier = Modifier.weight(10f)) { cuerpo() }
        }
        ScaffoldFrame(modifier = Modifier.weight(1f)) { pie() }
    }
}

@Preview(showBackground = true)
@Composable
fun MiScaffoldPreview() {
    EjemplosScaffoldTheme {
        MiScaffold(
            cabecera = { Text(text = "Cabecera",
                modifier = Modifier.padding(5.dp)) },
            menu = { Text(text = "Menu",
                modifier = Modifier.padding(5.dp)) },
            cuerpo = { Text(text = "Cuerpo",
                modifier = Modifier.padding(5.dp)) },
            pie = { Text(text = "Pie",
                modifier = Modifier.padding(5.dp)) }
        )
    }
}