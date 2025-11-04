package com.pmdm.ejerciciosTema31.ui.features

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.pmdm.ejerciciosTema31.ui.theme.EjerciciosTema31Theme
import kotlin.random.Random

@Composable
fun CompruebaNumeros(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var numero by remember {
            mutableIntStateOf(0)
        }
        var textoPrimo by remember {
            mutableStateOf("")
        }
        val onGeneraNumero:() -> Unit = {
            numero = Random.nextInt(1,50)
            textoPrimo = ""
        }
        val onComprobrobarEsPrimo:() -> Unit = {
            textoPrimo = "El numero "+ if(esPrimo(numero)){
                "es"
            } else{
                "no es"
            } + " primo"
        }

        Button(onClick = onGeneraNumero) {
            Text(text = "Genera numero")
        }
        Text(
            text = "El numero generado es $numero"
        )
        Button(onClick = onComprobrobarEsPrimo) {
            Text(text = "¿Es primo?")
        }
        Text(
            text = textoPrimo
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
fun CompruebaNumerosPreview() {
    EjerciciosTema31Theme {
        CompruebaNumeros()
    }
}