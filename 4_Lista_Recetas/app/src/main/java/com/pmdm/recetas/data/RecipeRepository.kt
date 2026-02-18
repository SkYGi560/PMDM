package com.pmdm.recetas.data

import com.pmdm.recetas.data.mocks.RecipeDaoMock
import com.pmdm.recetas.data.mocks.RecipeMock
import com.pmdm.recetas.models.Recipe

class RecipeRepository {
    val proveedorRecipe = RecipeDaoMock()

    fun get() = proveedorRecipe.get().toRecipe()
    fun get(id: Int) = proveedorRecipe.get(id)
    fun update(recipe: Recipe) = proveedorRecipe.update(recipe.toRecipeMock())

}