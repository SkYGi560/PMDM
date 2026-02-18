package com.pmdm.recetas.ui.features.recipe

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pmdm.recetas.R
import com.pmdm.recetas.ui.composables.buttonlike.ButtonLike
import com.pmdm.recetas.ui.theme.RecetasTheme


@Composable
fun RecipeScreen(
    recipes: MutableList<RecipeUiState>,
    onRecipeEvent: (RecipeEvent) -> Unit
)
{
    Box(
        modifier = Modifier.fillMaxHeight(),

        )
    {
        var pagerState = rememberPagerState(pageCount = { recipes.size })

        HorizontalPager(modifier = Modifier
            .wrapContentHeight()
            .padding(20.dp),
            state = pagerState,
        ) { page ->
            CardRecipe(recipe =recipes.get(page) , onRecipeEvent =
            {
                onRecipeEvent(it)
          //      pagerState=pagerState
            } )
        }
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(recipes.size) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(20.dp)
                )
            }
        }
    }
}


@Composable
fun CardRecipe(
    recipe: RecipeUiState,
    onRecipeEvent: (RecipeEvent) -> Unit
) {
    val contexto = LocalContext.current

    var painterFoto = recipe.recipeFoto.let {
       if(it!=null)
       {
           val imageResource =
               contexto.resources.getIdentifier(recipe.recipeFoto, "drawable", contexto.packageName)
           painterResource(id =imageResource )
       } else painterResource(id = R.drawable.local_dining_24px)
    }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Column {
            Surface(
                modifier = Modifier.clip(CardDefaults.shape),
                color = MaterialTheme.colorScheme.primary
            ) {

                Image(
                    modifier = Modifier.size(400.dp),
                    painter = painterFoto,
                    contentDescription = recipe.recipeName,
                    contentScale = ContentScale.Crop,
                    alpha = 0.90f
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                text = recipe.recipeName,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                text = recipe.recipeDescription,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = recipe.recipeChef,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
                ButtonLike(
                    iLike = recipe.iLike,
                    numberOfLikes = recipe.numberOfLikes,
                    onILikePressed = {onRecipeEvent(RecipeEvent.ClickButtonLike(recipe))}
                )
            }
        }
    }
}

class ConImagenRecetaProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}

@Preview(showBackground = true)
@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun RecetaPreview(@PreviewParameter(ConImagenRecetaProvider::class) conImagen: Boolean) {

    val viewModel:RecipeViewModel = viewModel()



    RecetasTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RecipeScreen(
                viewModel.recipeState,
                onRecipeEvent = viewModel::onRecipeEvent
            )
        }
    }
}
