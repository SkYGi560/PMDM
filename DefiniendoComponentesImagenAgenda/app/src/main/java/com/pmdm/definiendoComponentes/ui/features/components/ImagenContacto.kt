package com.pmdm.definiendoComponentes.ui.features.components

import android.R
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pmdm.definiendoComponentes.ui.theme.definiendoComponentesTheme

@Composable
fun ImagenContacto(
    modifier: Modifier,
    foto: ImageBitmap?,
    anchoBorde: Dp = 4.dp
){
    val painterFoto : Painter = if (foto != null){
        BitmapPainter(foto)
    } else {
        painterResource(com.pmdm.definiendoComponentes.R.drawable.face_2_24px)
    }
    Image(
        painter = painterFoto,
        contentScale = ContentScale.Crop,
        contentDescription = "Imagen Contacto",
        modifier = Modifier
            .clip(CircleShape)
            .aspectRatio(ratio = 1f)
            .background(MaterialTheme.colorScheme.surface)
            .border(width = anchoBorde, color = MaterialTheme.colorScheme.inversePrimary, shape = CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun ImagenContactoPreviewSinFoto(){
    definiendoComponentesTheme{
        Box(
            Modifier.background(color = MaterialTheme.colorScheme.primary).width(300.dp).height(200.dp)
        ){
            ImagenContacto(modifier = Modifier.fillMaxHeight(), foto = null)
        }
    }
}

@Composable
fun ImagenContactoPreviewConFotoYFondo(){
    val imagenFondo: Painter =
        if(isSystemInDarkTheme())
            painterResource(com.pmdm.definiendoComponentes.R.drawable.bg_dark)
        else
            painterResource(com.pmdm.definiendoComponentes.R.drawable.bg_light)
    val imagenContacto = ImageBitmap.imageResource(com.pmdm.definiendoComponentes.R.drawable.foto_prueba)

    Box(
        Modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .width(300.dp)
            .height(200.dp)
    ){
        Image(
            painter = imagenFondo,
            contentScale = ContentScale.FillBounds,
            contentDescription = "Imagen de fondo")

        ImagenContacto(
            modifier = Modifier.fillMaxHeight(),
            foto = imagenContacto)
    }
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewLight(){
    ImagenContactoPreviewConFotoYFondo()
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDark(){
    ImagenContactoPreviewConFotoYFondo()
}