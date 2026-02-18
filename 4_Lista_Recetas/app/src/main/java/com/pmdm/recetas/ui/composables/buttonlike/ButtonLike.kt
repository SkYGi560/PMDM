package com.pmdm.recetas.ui.composables.buttonlike

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Badge
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.pmdm.recetas.ui.theme.RecetasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonLike(
    iLike: Boolean,
    numberOfLikes: Int,
    onILikePressed: () -> Unit
) {
    Box {
        IconButton(
            modifier = Modifier
                .clip(ButtonDefaults.filledTonalShape)
                .background(ButtonDefaults.buttonColors().containerColor),
            onClick =  onILikePressed
        ) {
            Icon(
                modifier = Modifier.size(ButtonDefaults.IconSize),
                imageVector = Icons.Filled.Favorite,
                contentDescription = "I Like Symbol",
                tint = if (iLike)
                    Color.Red
                else
                    ButtonDefaults.buttonColors().contentColor
            )
        }
        Badge(modifier = Modifier.align(Alignment.BottomEnd)) {
            Text(text = if (numberOfLikes< 100) numberOfLikes.toString() else "99+")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview1() {
    var iLikeState by remember { mutableStateOf(false) }
    var numberOfLikesState by remember { mutableIntStateOf(4) }

    var onILikePressed: () -> Unit = {
        iLikeState = !iLikeState
        if (iLikeState) {
            numberOfLikesState++
        } else {
            numberOfLikesState--
        }
    }

    var iLikeState2 by remember { mutableStateOf(true) }
    var numberOfLikesState2 by remember { mutableIntStateOf(100) }

    var onILikePressed2: () -> Unit = {
        iLikeState2 = !iLikeState2
        if (iLikeState2) {
            numberOfLikesState2++
        } else {
            numberOfLikesState2--
        }
    }

    Row {
        RecetasTheme {
            ButtonLike(
                iLike = iLikeState,
                numberOfLikes = numberOfLikesState,
                onILikePressed = onILikePressed
            )
            ButtonLike(
                iLike =iLikeState2,
                numberOfLikes = numberOfLikesState2,
                onILikePressed = onILikePressed2
            )
        }
    }
}