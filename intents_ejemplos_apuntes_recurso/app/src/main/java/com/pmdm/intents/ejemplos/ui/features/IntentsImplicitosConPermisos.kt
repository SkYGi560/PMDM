package com.pmdm.intents.ejemplos.ui.features

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.pmdm.intents.ejemplos.ui.theme.EjemplosIntentsTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

private fun Context.toImageBitmap(uri: Uri): ImageBitmap {
    val contextResolver = this.contentResolver
    val source = ImageDecoder.createSource(contextResolver, uri)
    return ImageDecoder.decodeBitmap(source).asImageBitmap()
}

@Composable
fun registroLlamarPorTelefonoIntent(
    telefono: String
): ManagedActivityResultLauncher<String, Boolean> {
    val context = LocalContext.current
    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { success ->
        if (success) {
            Intent(Intent.ACTION_CALL).also { callIntent ->
                callIntent.data = Uri.parse("tel:$telefono")
                context.startActivity(callIntent)
            }
        }
    }
}

@Composable
fun registroHacerFotoConIntent(
    onFotoCambiada: (ImageBitmap) -> Unit
): ManagedActivityResultLauncher<String, Boolean> {
    val cameraLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts
                .StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val androidBitmap = result.data?.extras?.get("data") as Bitmap
                onFotoCambiada(androidBitmap!!.asImageBitmap())
            }
        }

    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { success ->
        if (success) {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
            cameraLauncher.launch(cameraIntent)
        }
    }
}

@Composable
fun registroHacerFotoConTakePicture(
    onFotoCambiada: (ImageBitmap) -> Unit
): ManagedActivityResultLauncher<String, Boolean> {

    val context = LocalContext.current
    val ficheroTemporal = File.createTempFile(
        "JPEG_${SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())}_",
        ".jpg",
        context.externalCacheDir
    )
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        ficheroTemporal
    )
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                onFotoCambiada(context.toImageBitmap(uri))
            }
        }
    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { success ->
        if (success) {
            cameraLauncher.launch(uri)
        }
    }
}

@Composable
fun registroSelectorDeImagenesConGetContent(
    onFotoCambiada: (ImageBitmap) -> Unit
): ManagedActivityResultLauncher<String, Uri?> {
    val context = LocalContext.current
    return rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            onFotoCambiada(context.toImageBitmap(uri))
        }
    }
}

@Composable
fun IntentLlamadaConPermisos(telefono: String) {
    val launcherTelefono = registroLlamarPorTelefonoIntent(telefono)
    Button(onClick = {
        launcherTelefono.launch(android.Manifest.permission.CALL_PHONE)
    }) {
        Text(text = "Llamar al $telefono")
    }
}

@Composable
fun IntentFotoConPermisos(
    onFotoCambiada: (ImageBitmap) -> Unit
) {
    val launcherHacerFoto = registroHacerFotoConTakePicture(onFotoCambiada)
    Button(onClick = {
        launcherHacerFoto.launch(android.Manifest.permission.CAMERA)
    }) {
        Text(text = "Hacer foto")
    }
}

@Composable
fun IntentImagenGaleria(
    onFotoCambiada: (ImageBitmap) -> Unit
) {
    val launcherFotoGaleria = registroSelectorDeImagenesConGetContent(onFotoCambiada)
    Button(onClick = {
        launcherFotoGaleria.launch("image/*")
    }) {
        Text(text = "Foto galería")
    }
}

@Preview
@Composable
fun IntentsImplicitosConPermisosPreview() {

    var foto by rememberSaveable { mutableStateOf<ImageBitmap?>(null) }
    
    EjemplosIntentsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                IntentLlamadaConPermisos("666666666")
                IntentFotoConPermisos { foto = it }
                IntentImagenGaleria { foto = it }
                foto?.let {
                    Image(
                        bitmap = it,
                        contentDescription = "foto",
                        modifier = Modifier.size(300.dp),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                }
            }
        }
    }
}
