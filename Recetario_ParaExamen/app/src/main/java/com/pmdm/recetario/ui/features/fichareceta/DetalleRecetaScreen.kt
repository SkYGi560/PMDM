package com.pmdm.recetario.ui.features.fichareceta

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pmdm.recetario.model.Receta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarTransparente(
    onVolverClick: () -> Unit
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(
                onClick = onVolverClick,
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Black.copy(alpha = 0.3f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .background(Color.Black.copy(alpha = 0.3f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Compartir",
                    tint = Color.White
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .background(Color.Black.copy(alpha = 0.3f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Más opciones",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        modifier = Modifier.statusBarsPadding()
    )
}


@Composable
private fun ImagenOscurecida(
    urlImagen: String?,
    descripcionImagen: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(data = urlImagen)
                .crossfade(enable = true)
                .build(),
            contentDescription = descripcionImagen,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.1f))
        )
    }
}

@Composable
private fun DatosChef(chef: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            shape = CircleShape,
            color = Color(red = 255, green = 236, blue = 179, alpha = 255),
            modifier = Modifier.size(40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Restaurant,
                    contentDescription = null,
                    tint = Color(red = 230, green = 74, blue = 25, alpha = 255),
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = chef,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = Color(red = 230, green = 74, blue = 25, alpha = 255)
            )
        )
    }
}

@Composable
private fun NombreRecetaMasHacerFavorita(
    nombre: String,
    favorita: Boolean,
    onFavoritaClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = nombre,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F1F1F),
                lineHeight = 32.sp
            ),
            modifier = Modifier.weight(weight = 1f)
        )

        IconButton(
            onClick = onFavoritaClick,
            modifier = Modifier.size(size = 48.dp)
        ) {
            Icon(
                imageVector = if (favorita) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Me gusta",
                tint = if (favorita) Color(
                    red = 255,
                    green = 0,
                    blue = 0,
                    alpha = 150
                ) else Color.Gray,
                modifier = Modifier.size(size = 32.dp)
            )
        }
    }
}

@Composable
fun DetalleRecetaScreen(
    recetaState: Receta,
    onFavoritaClick: () -> Unit,
    onVolverClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Surface(color = Color.White) {
            Column(modifier = Modifier.fillMaxSize()) {
                ImagenOscurecida(
                    urlImagen = recetaState.foto,
                    descripcionImagen = "Foto de ${recetaState.nombre}",
                    modifier = Modifier
                        .weight(weight = 1f)
                        .fillMaxWidth()
                )

                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-32).dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 32.dp)
                            .fillMaxWidth()
                    ) {
                        NombreRecetaMasHacerFavorita(
                            nombre = recetaState.nombre,
                            favorita = recetaState.favorita,
                            onFavoritaClick = onFavoritaClick
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        DatosChef(chef = recetaState.chef)

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = recetaState.descripcion,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color(red = 0, green = 0, blue = 0, alpha = 200),
                                lineHeight = 24.sp
                            )
                        )
                    }
                }
            }
        }

        TopBarTransparente(onVolverClick = onVolverClick)
    }
}


@Preview(showBackground = true, device = "id:pixel_8")
@Composable
fun PreviewDetalleReceta() {
    val recetaMock = Receta(
        id = 8,
        nombre = "Fish and Chips Gourmet",
        descripcion = "Pescado blanco fresco rebozado en una masa ligera de cerveza, servido con patatas fritas gruesas y puré de guisantes a la menta.",
        chef = "Gordon Ramsay",
        foto = "http://10.0.2.2/recetas/8.jpg",
        favorita = false
    )
    MaterialTheme {
        Surface {
            DetalleRecetaScreen(
                recetaState = recetaMock,
                onFavoritaClick = {},
                onVolverClick = {}
            )
        }
    }
}