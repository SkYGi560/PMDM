package com.pmdm.recetario.ui.features.listarecetas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pmdm.recetario.model.Receta
import com.pmdm.recetario.ui.theme.RecetarioTheme

@Composable
fun ItemReceta(
    receta: Receta,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    seleccionado: Boolean = false
) {
    Surface(
        onClick = onClick,
        modifier =
            modifier.then(Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)),
        shape = RoundedCornerShape(28.dp),
        shadowElevation = 6.dp,
        color = if (seleccionado) Color(red = 255, green = 224, blue = 178, alpha = 255) else MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(data = receta.foto)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = "Foto de ${receta.nombre}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(20.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Restaurant,
                            contentDescription = null,
                            tint = Color(230, 74, 25, 255),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = receta.chef,
                            style = MaterialTheme.typography.labelLarge,
                            color = Color(230, 74, 25, 255),
                        )
                    }
                }
                Row {
                    if (receta.favorita) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Marcada como favorito",
                            tint = Color(255, 0, 0, 150),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(
                        text = receta.nombre,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = receta.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0)
@Composable
fun PreviewItemReceta() {
    val receta = Receta(
        id = 8,
        nombre = "Fish and Chips Gourmet",
        descripcion = "Pescado blanco fresco rebozado en una masa ligera de cerveza, servido con patatas fritas gruesas y puré de guisantes a la menta.",
        chef = "Gordon Ramsay",
        foto = "http://10.0.2.2/recetas/8.jpg",
        favorita = true
    )
    RecetarioTheme {
        Surface {
            Box(modifier = Modifier.padding(16.dp)) {
                ItemReceta(
                    receta =receta,
                    onClick = {}
                )
            }
        }
    }
}