package com.pmdm.recetas.ui.features.receta

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.recetas.ui.composables.buttonLike.ButtonLike
import com.pmdm.recetas.ui.theme.RecetasTheme

data class ButtonLikeUiState(
    val numberOfLikes: Int,
    val iLike: Boolean
)

@Composable
fun Receta(
    iLike: ButtonLikeUiState,
    recipeName: String,
    recipeDescription: String,
    recipeChef: String,
    onILikePressed: () -> Unit
) {
    Card {
        Surface(
            modifier = Modifier.clip(CardDefaults.shape),
            color = MaterialTheme.colorScheme.primary
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = com.pmdm.recetas.R.drawable.magdalenas),
                contentDescription = "Magadalendas",
                contentScale = ContentScale.FillWidth,
                alpha = 0.8f
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp),
            text = recipeName,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp),
            text = recipeDescription,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp),
            text = recipeChef,
            style = MaterialTheme.typography.bodyMedium
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalArrangement = Arrangement.End
        ) {

            ButtonLike(
                iLike.iLike,
                iLike.numberOfLikes,
                onILikePressed = onILikePressed)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RecetaPreview() {

    var iLikeState by remember {
        mutableStateOf(ButtonLikeUiState(0,false))
    }
    val onLocallyLikePressed =
        {
            iLikeState = if(iLikeState.iLike)
                iLikeState.copy(iLikeState.numberOfLikes+ 1,false)
            else
                iLikeState.copy(iLikeState.numberOfLikes+ 1,true)
        }
    val onRemoteLikePresed =
        {
            iLikeState = iLikeState.copy(iLikeState.numberOfLikes+ 1,true)
        }

    RecetasTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Receta(
                iLike = iLikeState,//iLikeState
                recipeName = "Magdalemas de la abuela",
                recipeDescription = "Fabulosas magdalenas con pepitas de chocolate y un suave sabor a naranja.",
                recipeChef = "Carlos Arguiñano",
                onILikePressed = {onLocallyLikePressed}//onLocallyLikePressed
            )
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = {onRemoteLikePresed}) { //onRemoteLikePressed
                Text(text = "Otro usuario pulsa Like")
            }
        }
    }
}