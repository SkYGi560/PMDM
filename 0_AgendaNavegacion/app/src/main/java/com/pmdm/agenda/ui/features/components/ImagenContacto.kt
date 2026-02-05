package com.pmdm.agenda.ui.features.components

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pmdm.agenda.R
import com.pmdm.agenda.ui.theme.AgendaTheme

@Composable
fun ImagenContacto(
    modifier: Modifier = Modifier,
    foto : ImageBitmap?,
    anchoBorde : Dp = 4.dp,
) {
    val imagenSinFoto = painterResource(id = R.drawable.face_2_24px)
    var painterFoto = remember(foto) {
        foto?.let { BitmapPainter(it) } ?: imagenSinFoto
    }
    Image(
        modifier = modifier.then(
            Modifier
                .clip(CircleShape)
                .aspectRatio(ratio = 1f)
                .background(MaterialTheme.colorScheme.surface)
                .border(
                    width = anchoBorde,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    shape = CircleShape
                )),
        painter = painterFoto,
        contentScale = ContentScale.Crop,
        contentDescription = "Imagen contacto")
}

@Preview(showBackground = true)
@Composable
fun ImagenContactoPreviewSinFoto() {
    AgendaTheme {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            ImagenContacto(
                modifier = Modifier.fillMaxHeight(),
                foto = null
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ImagenContactoPreviewConFotoYFondo() {
    AgendaTheme {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            val bitmapFoto = ImageBitmap.imageResource(id = R.drawable.foto_prueba)
            val painterBg =  painterResource(
                id = if (isSystemInDarkTheme()) R.drawable.bg_dark else R.drawable.bg_light
            )

            Image(
                painterBg,
                contentDescription = "Fondo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
            ImagenContacto(
                modifier = Modifier.fillMaxHeight(),
                foto = bitmapFoto
            )
        }
    }
}
