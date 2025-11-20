package com.pmdm.recetas.data.mocks

class RecipeDaoMock {
    private var recipe =
        RecipeMock(
            name = "Magdalenas de la abuela",
            description = "Fabulosas magdalenas con pepitas de chocolate y un suave sabor",
            chef = "Carlos Arguiñano",
            photo = null,
            likes = 8,
            isLiked = false
        )
    fun get(): RecipeMock = recipe
    fun updateRecipe(recipeRemote:RecipeMock){
        recipe=recipeRemote
    }

}