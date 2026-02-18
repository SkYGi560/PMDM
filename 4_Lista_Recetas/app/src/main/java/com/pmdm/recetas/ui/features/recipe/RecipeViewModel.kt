package com.pmdm.recetas.ui.features.recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmdm.recetas.data.RecipeRepository
import com.pmdm.recetas.data.toRecipe
import com.pmdm.recetas.data.toRecipeUiState


class RecipeViewModel(
) : ViewModel() {
    private val repository: RecipeRepository = RecipeRepository()
    var recipeState by mutableStateOf(loadRecipe())

    fun loadRecipe() = repository.get().toRecipeUiState().toMutableStateList()

    fun onRecipeEvent(event: RecipeEvent) {
        when (event) {
            is RecipeEvent.ClickButtonLike -> {
               val posicion=recipeState.indexOf(event.recipe)
                if(posicion!=-1) {
                    val newLikes =
                        if (!recipeState[posicion].iLike) recipeState[posicion].numberOfLikes + 1 else recipeState[posicion].numberOfLikes - 1
                    recipeState[posicion] = recipeState[posicion].copy(
                        iLike = !recipeState[posicion].iLike,
                        numberOfLikes = newLikes
                    )
                    repository.update(recipeState[posicion].toRecipe())
                }
            }
           /* is RecipeEvent.Cambios ->{
                recipeState=recipeState
            }*/
        }
    }
}

