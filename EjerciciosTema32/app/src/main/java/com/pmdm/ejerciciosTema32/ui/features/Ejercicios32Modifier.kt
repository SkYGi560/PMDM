package com.pmdm.ejerciciosTema32.ui.features

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.ejerciciosTema32.ui.theme.EjerciciosTema32Theme

@Composable
fun TextoConFormato(texto:String,
                    color: Color = MaterialTheme.colorScheme.inversePrimary,
                    style: TextStyle = MaterialTheme.typography.headlineMedium,
                    modifier: Modifier = Modifier.fillMaxWidth().padding(10.dp)
){
    Text(
        text = texto,
        color = color,
        style = style,
        modifier = modifier
    )
}
private fun Modifier.estiloCiclos(color: Color) =
        background(color, RoundedCornerShape(3.dp))
        .padding(10.dp)
        .fillMaxWidth()

@Preview(showBackground = true)
@Composable
fun InformacionScreen(){
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondary)
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Column(
            modifier = Modifier.fillMaxWidth()
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.inversePrimary,
                    MaterialTheme.shapes.medium),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            TextoConFormato(
                texto = "IES DoctorBalmis",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(0.dp,10.dp,0.dp,0.dp))
            TextoConFormato(
                texto = "Ciclos Formativos Informatica",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(0.dp,0.dp,0.dp,10.dp))
        }

        TextoConFormato(
            texto = "DAM",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.estiloCiclos(color = MaterialTheme.colorScheme.errorContainer))
        TextoConFormato(
            texto = "DAW",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.estiloCiclos(color = MaterialTheme.colorScheme.tertiaryContainer))
        TextoConFormato(
            texto = "ASIR",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.estiloCiclos(color = MaterialTheme.colorScheme.secondaryContainer))
        TextoConFormato(
            texto = "SMR",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.estiloCiclos(color = MaterialTheme.colorScheme.primaryContainer))
    }
}
