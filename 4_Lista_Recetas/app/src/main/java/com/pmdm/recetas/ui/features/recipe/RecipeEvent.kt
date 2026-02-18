package com.pmdm.recetas.ui.features.recipe

sealed interface RecipeEvent{
    data class ClickButtonLike(val recipe:RecipeUiState):RecipeEvent
  //object Cambios:RecipeEvent
}