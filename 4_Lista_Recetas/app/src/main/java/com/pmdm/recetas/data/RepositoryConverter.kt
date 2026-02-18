package com.pmdm.recetas.data

import com.pmdm.recetas.data.mocks.RecipeMock
import com.pmdm.recetas.models.Recipe
import com.pmdm.recetas.ui.features.recipe.RecipeUiState

fun RecipeMock.toRecipe(): Recipe = Recipe(
    this.id,
    this.name,
    this.description,
    this.chef,
    this.photo,
    this.likes,
    this.isLiked
)

fun Recipe.toRecipeMock(): RecipeMock = RecipeMock(
    this.id,
    this.name,
    this.description,
    this.chef,
    this.photo,
    this.likes,
    this.isLiked
)

fun Recipe.toRecipeUiState(): RecipeUiState = RecipeUiState(
    this.id,
    this.name,
    this.description,
    this.chef,
    this.photo,
    this.likes,
    this.isLiked
)

fun RecipeUiState.toRecipe(): Recipe = Recipe(
    this.id,
    this.recipeName,
    this.recipeDescription,
    this.recipeChef,
    this.recipeFoto,
    this.numberOfLikes,
    this.iLike
)

fun MutableList<RecipeMock>.toRecipe() = this.map { it.toRecipe() }.toMutableList()
fun MutableList<Recipe>.toRecipeUiState() = this.map { it.toRecipeUiState() }.toMutableList()

