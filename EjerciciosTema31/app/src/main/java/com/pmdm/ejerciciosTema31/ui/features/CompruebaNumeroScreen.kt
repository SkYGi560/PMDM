package com.pmdm.ejerciciosTema31.ui.features

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.pmdm.ejerciciosTema31.ui.theme.EjerciciosTema31Theme
import kotlin.random.Random

@Composable
fun Comprobador(
    numeroState: Int,
    onGeneraNumero: () -> Unit
){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onGeneraNumero) {
            Text("Generar Número")
        }
        Text(
            "El número generado es $numeroState"
        )
    }

}
@Composable
fun EsPrimoComposable(
    esPrimoTextoState: String,
    onEsPrimo: () -> Unit
){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onEsPrimo) {
            Text("¿Es primo?")
        }
        Text(esPrimoTextoState)
    }
}
@Composable
fun CompruebaNumeroScreen(){
    var numeroState by rememberSaveable { mutableIntStateOf(0) }
    var esPrimoTextoState by rememberSaveable { mutableStateOf("") }
    val onGeneraNumero = {numeroState = Random.nextInt(1,50) }
    val onEsPrimo = {esPrimoTextoState = "El numero "+ if(esPrimo(numeroState)){
        "es"
    } else{
        "no es"
    } + " primo"}

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Comprobador(
            numeroState,
            onGeneraNumero
        )
        EsPrimoComposable(
            esPrimoTextoState,
            onEsPrimo
        )
    }
}
private fun esPrimo(numero:Int): Boolean{
    if(numero < 2) return false
    for(i in 2..numero-1){
        if(numero%i == 0)
            return false
    }
    return true
}
@Preview(showBackground = true)
@Composable
fun CompruebaNumerosPreview2(){
    EjerciciosTema31Theme{
        CompruebaNumeroScreen()
    }
}