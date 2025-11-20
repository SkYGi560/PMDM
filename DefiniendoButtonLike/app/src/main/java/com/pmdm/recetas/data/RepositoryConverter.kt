package com.pmdm.recetas.data

import com.pmdm.recetas.data.mocks.RecipeMock
import com.pmdm.recetas.models.Recipe

fun Recipe.toRecipeMock(): RecipeMock = RecipeMock(
    name = this.name,
    description = this.description,
    chef = this.chef,
    photo = this.photo,
    likes = this.likes,
    isLiked = this.isLiked
)

fun RecipeMock.toRecipe(): Recipe = Recipe(
    name = this.name,
    description = this.description,
    chef = this.chef,
    photo = this.photo,
    likes = this.likes,
    isLiked = this.isLiked
)