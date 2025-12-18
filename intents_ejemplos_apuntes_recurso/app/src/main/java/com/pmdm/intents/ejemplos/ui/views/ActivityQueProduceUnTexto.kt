package com.pmdm.intents.ejemplos.ui.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.intents.ejemplos.ui.theme.EjemplosIntentsTheme

class ActivityQueProduceUnTexto : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Recuperamos el texto que nos ha pasado el llamador y que está en
        // la propiedad intent de la actividad que contiene los datos
        // que se han pasado desde el llamador.
        // Los asignamos en un estado usado por la interfaz en la composición.
        val textoRecibidoPorLlamador by mutableStateOf(
            intent.getStringExtra("TEXTO") ?: ""
        )

        val onClickDevolver: (String) -> Unit = { texto ->
            // Para devolver datos al llamador, los empaquetamos en un
            // Intent le añadimos los datos y lo pasamos como parámetro
            // al método setResult, que crea un objeto ActivityResult que
            // es lo que espera recibir el llamador en su 'contrarto'.
            Intent().also {intento ->
                intento.putExtra("TEXTODEVUELTO", texto)
                setResult(RESULT_OK, intento)
            }
            // Finalizamos la actividad tras poner los datos de resultado en el intent
            finish()
        }

        setContent {
            EjemplosIntentsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PideTexto(
                        textoRecibidoPorLlamador = textoRecibidoPorLlamador,
                        onClickDevolver = onClickDevolver
                    )
                }
            }
        }
    }
}

@Composable
fun PideTexto(
    textoRecibidoPorLlamador: String = "Sin llamador",
    onClickDevolver: (String) -> Unit = {}
) {
    var texto by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = textoRecibidoPorLlamador,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            label = { Text(text = "Texto a devolver") },
            value = texto,
            onValueChange = { texto = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onClickDevolver(texto) }) {
            Text(text = "Devolver texto")
        }
    }
}

@Preview (showBackground = true, device = "id:pixel_3a")
@Composable
fun ActivityQueProduceUnTextoPreview() {
    EjemplosIntentsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            PideTexto()
        }
    }
}