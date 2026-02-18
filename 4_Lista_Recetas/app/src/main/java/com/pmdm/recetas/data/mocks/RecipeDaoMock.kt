package com.pmdm.recetas.data.mocks

class RecipeDaoMock {
    //solo tengo una receta
    private var recipes = mutableListOf<RecipeMock>(
        RecipeMock(
            id=1,
            name = "Magdalenas de la abuela",
            description = "Fabulosas magdalenas con pepitas de chocolate y un suave sabor a naranja.",
            chef = "Carlos Arguiñano",
            photo = "magdalenas",
            likes = 8,
            isLiked = false
        ),
        RecipeMock(
            id=2,
            name = "Pan casero con tomates secos",
            description = "Novedoso pan casero con un estimulante sabor a tomates secos, perfecto para acompañar platos de cuchara",
            chef = "Carlos Arguiñano",
            photo = "pan",
            likes = 3,
            isLiked = false
        ),
        RecipeMock(
            id=3,
            name = "Ensalada multicolor con nueces",
            description = "Receta sencilla y rápida que dejará impresionados a todos tus invitados.",
            chef = "Sonia de la Oz",
            photo = "ensalada",
            likes = 5,
            isLiked = false
        ),
        RecipeMock(
            id=4,
            name = "Caldo de almendras, pasas y calabaza",
            description = "Estupenda sopa vejetal de invierno, con un sabor exótico inmejorable",
            chef = "Mohamed Aldalay",
            photo = null,
            likes = 12,
            isLiked = false
        ),
    )

    fun get(): MutableList<RecipeMock> = recipes
    fun get(id: Int): RecipeMock? = recipes.find { r -> r.id == id }
    fun update(recipe:RecipeMock) {
        val position=recipes.indexOf(get(recipe.id) )
        recipes[position]=recipe
    }

}