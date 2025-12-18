package com.pmdm.intents.ejemplos.ui.features

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.pmdm.intents.ejemplos.R
import com.pmdm.intents.ejemplos.ui.theme.EjemplosIntentsTheme

fun Context.sendMail(
    correos: Array<String>,
    asunto: String,
    texto: String,
    forzarEleccion: Boolean = false
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        // Añadimos los datos del correo.
        putExtra(Intent.EXTRA_EMAIL, correos)
        putExtra(Intent.EXTRA_SUBJECT, asunto)
        putExtra(Intent.EXTRA_TEXT, texto)
    }
    val chooser = if (forzarEleccion) {
        val title: String = resources.getString(R.string.enviar_correo)
        Intent.createChooser(intent, title)
    } else null

    if (intent.resolveActivity(packageManager) != null) {
        startActivity(chooser ?: intent)
    }
}

fun Context.buscaEnMaps(lugar: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("geo:0,0?q=$lugar")
    }
    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, "No se puede abrir Maps", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun IntentSendMail() {
    val context = LocalContext.current
    Button(onClick = {
        context.sendMail(
            correos = arrayOf("correo@alu.edu.gva.es"),
            asunto = "Asunto del correo",
            texto = "Texto del correo",
            forzarEleccion = true
        )
    }) {
        Text(text = "Send Mail")
    }
}

@Composable
fun IntentBuscaEnMaps() {
    val context = LocalContext.current
    Button(onClick = {
        context.buscaEnMaps("I.E.S Doctor Balmis, +Alicante")
    }) {
        Text(text = "Ver Balmis en Maps")
    }
}

@Preview
@Composable
fun IntentsImplicitosPreview() {
    EjemplosIntentsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly) {
                IntentSendMail()
                IntentBuscaEnMaps()
            }
        }
    }
}
