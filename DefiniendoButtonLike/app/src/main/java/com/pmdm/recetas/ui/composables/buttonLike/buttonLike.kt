package com.pmdm.recetas.ui.composables.buttonLike

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Badge
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonLike(
    iLike: Boolean,             // Estado que indica si se ha pulsado like o no.
    numberOfLikes: Int,         // Número de likes que llevamos.
    onILikePressed: () -> Unit  // Función que se ejecutará cuando se pulse el botón.
){
    Box{
        IconButton(onILikePressed, Modifier.clip(ButtonDefaults.shape).background(
            ButtonDefaults.buttonColors().containerColor)) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Boton like",
                modifier = Modifier.size(
                    ButtonDefaults.IconSize
                ),
                tint = if(iLike) Color.Red else Color.White
            )
        }
        Badge(modifier = Modifier.align(Alignment.BottomEnd)) {
            Text("${if(numberOfLikes>99) "99+" else numberOfLikes}")
        }
    }
}