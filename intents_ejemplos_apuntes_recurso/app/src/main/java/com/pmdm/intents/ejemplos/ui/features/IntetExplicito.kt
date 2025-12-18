package com.pmdm.intents.ejemplos.ui.features

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.pmdm.intents.ejemplos.ui.theme.EjemplosIntentsTheme

fun Context.openChrome() {
    // Creamos un Intent con la acción ACTION_MAIN
    // que es abrir una actividad principal de la aplicación Chrome.
    Intent(Intent.ACTION_MAIN).also {
        it.`package` = "com.android.chrome"
        // Lanza ActivityNotFoundException si no está instalada la aplicación.
        startActivity(it)
    }
}

@Composable
fun IntentOpenChrome() {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center) {
        Button(onClick = { context.openChrome() }) {
            Text(text = "Open Chrome")
        }
    }
}

@Preview
@Composable
fun IntentOpenChromePreview() {
    EjemplosIntentsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            IntentOpenChrome()
        }
    }
}
