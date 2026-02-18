package com.pmdm.recetas.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pmdm.recetas.ui.features.recipe.RecipeScreen
import com.pmdm.recetas.ui.features.recipe.RecipeViewModel
import com.pmdm.recetas.ui.theme.RecetasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel:RecipeViewModel by viewModels()
            RecetasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeScreen(
                        viewModel.recipeState,
                        onRecipeEvent = viewModel::onRecipeEvent
                    )
                }
            }
        }
    }
}

