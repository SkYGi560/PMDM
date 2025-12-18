package com.pmdm.intents.ejemplos.ui.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.intents.ejemplos.ui.theme.EjemplosIntentsTheme

class MainActivity : ComponentActivity() {
    private var textoDevueltoPorIntent by mutableStateOf("No has llamdo aún")

    // Definimos un lanzador con el contrato ActivityResultContracts.StartActivityForResult
    // que recibirá (ENTRADA) el intent explícito sobre la actividad secuendaria
    // y devolverá (SALIDA) un objeto ActivityResult que contiene el intent de respuesta
    private val launcherActivityQueProduceUnTexto: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                textoDevueltoPorIntent = result.data?.getStringExtra("TEXTODEVUELTO")
                    ?: "Nada retornado"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemplosIntentsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val context = LocalContext.current
                    InterfaceParaLanzarActivityQueProduceUnTexto(
                        textoDevueltoPorIntent = textoDevueltoPorIntent,
                        onClickLanzar = {
                            // En lanzado con el contrato encargado de gestionar una
                            // respuesta ActivityResult. Para ello crea el
                            // Intent explícito pasándole un texto como parámetro
                            // indicándole por quien es llamada.
                            // El contexto se puede sacar de LocalContext.current y también
                            // claururándo el propio contexto de la actividad.
                            launcherActivityQueProduceUnTexto.launch(
                                Intent(context, ActivityQueProduceUnTexto::class.java)
                                    .apply {
                                        putExtra("TEXTO", "Te llamo desde MainActivity")
                                    }
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun InterfaceParaLanzarActivityQueProduceUnTexto(
    textoDevueltoPorIntent: String = "No has llamdo aún",
    onClickLanzar: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = onClickLanzar) {
            Text(text = "Llama a ActivityQueProduceUnTexto")
        }
        Text(
            text = textoDevueltoPorIntent,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_3a")
@Composable
fun MainActivityPreview() {
    EjemplosIntentsTheme {
        Surface {
            InterfaceParaLanzarActivityQueProduceUnTexto()
        }
    }
}