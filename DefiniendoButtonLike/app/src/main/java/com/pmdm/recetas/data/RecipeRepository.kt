package com.pmdm.recetas.data

import com.pmdm.recetas.data.mocks.RecipeDaoMock
import com.pmdm.recetas.models.Recipe

class RecipeRepository {
    private var dao = RecipeDaoMock()

    fun get(): Recipe = dao.get().toRecipe()
    fun likePress() {
        val nuevaRecipe = dao.get().copy(
            isLiked = !dao.get().isLiked,
            likes = if(dao.get().isLiked) dao.get().likes +1 else dao.get().likes -1
        )
        dao.updateRecipe(nuevaRecipe)
    }
}
