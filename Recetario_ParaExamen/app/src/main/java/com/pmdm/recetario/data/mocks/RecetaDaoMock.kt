package com.pmdm.recetario.data.mocks

import kotlinx.coroutines.coroutineScope

class RecetaDaoMock {

    val recetas: MutableList<RecetaMock> = mutableListOf(
        RecetaMock(
            id = 1,
            nombre = "Merluza a la Koxkera",
            descripcion = "Tradicional plato vasco de merluza en salsa verde con almejas, guisantes y huevo duro. Un clásico de la cocina marinera.",
            chef = "Carlos Arguiñano",
            foto = "http://10.0.2.2/recetas/1.jpg"
        ),
        RecetaMock(
            id = 2,
            nombre = "Pollo al Ajillo con Patatas",
            descripcion = "Receta sencilla y sabrosa de pollo dorado con mucho ajo, vino blanco y hierbas, acompañado de patatas fritas caseras.",
            chef = "Carlos Arguiñano",
            foto = "http://10.0.2.2/recetas/2.jpg",
            favorita = true
        ),
        RecetaMock(
            id = 11,
            nombre = "Patatas a la Riojana",
            descripcion = "Guiso contundente de patatas con chorizo, pimiento choricero y pimentón. Un plato de cuchara imprescindible.",
            chef = "Carlos Arguiñano",
            foto = "http://10.0.2.2/recetas/11.jpg"
        ),
        RecetaMock(
            id = 12,
            nombre = "Bacalao al Pil-Pil",
            descripcion = "Emblema de la cocina vasca. Lomos de bacalao confitados en aceite, ajo y guindilla, ligando la salsa con la gelatina del pescado.",
            chef = "Carlos Arguiñano",
            foto = "http://10.0.2.2/recetas/12.jpg"
        ),
        RecetaMock(
            id = 13,
            nombre = "Marmitako de Bonito",
            descripcion = "Guiso marinero de bonito del norte con patatas, cebolla, pimiento y tomate. Sabor a mar en cada cucharada.",
            chef = "Carlos Arguiñano",
            foto = "http://10.0.2.2/recetas/13.jpg",
            favorita = true
        ),
        RecetaMock(
            id = 14,
            nombre = "Carrilleras al Vino Tinto",
            descripcion = "Carne melosa estofada lentamente en salsa de vino tinto y verduras hasta que se deshace en la boca.",
            chef = "Carlos Arguiñano",
            foto = "http://10.0.2.2/recetas/14.jpg"
        ),
        RecetaMock(
            id = 15,
            nombre = "Sopa de Ajo Castellana",
            descripcion = "Sopa humilde y reconfortante a base de pan duro, ajo, pimentón, jamón y huevo escalfado.",
            chef = "Carlos Arguiñano",
            foto = "http://10.0.2.2/recetas/15.jpg"
        ),

        RecetaMock(
            id = 3,
            nombre = "Ratatouille Provençal",
            descripcion = "Guiso de hortalizas típico de la Provenza francesa. Incluye tomate, ajo, pimientos, cebolla, calabacín y berenjena, todo cocinado lentamente.",
            chef = "Sonia de la Oz",
            foto = "http://10.0.2.2/recetas/3.jpg"
        ),
        RecetaMock(
            id = 4,
            nombre = "Quiche Lorraine",
            descripcion = "Tarta salada originaria de la región de Lorena. Base de masa brisa rellena de una mezcla de crema de leche, huevos y panceta ahumada.",
            chef = "Sonia de la Oz",
            foto = "http://10.0.2.2/recetas/4.jpg",
            favorita = true
        ),

        RecetaMock(
            id = 5,
            nombre = "Risotto de Setas Porcini",
            descripcion = "Arroz cremoso cocinado lentamente con caldo de verduras, vino blanco, setas porcini deshidratadas y abundante queso parmesano.",
            chef = "Massimo Bottura",
            foto = "http://10.0.2.2/recetas/5.jpg"
        ),
        RecetaMock(
            id = 6,
            nombre = "Lasaña Clásica a la Boloñesa",
            descripcion = "Capas alternas de pasta, salsa de carne (ragú) cocinada a fuego lento, bechamel cremosa y queso gratinado al horno.",
            chef = "Massimo Bottura",
            foto = "http://10.0.2.2/recetas/6.jpg"
        ),

        RecetaMock(
            id = 7,
            nombre = "Solomillo Wellington",
            descripcion = "Filete de ternera envuelto en duxelles de champiñones, jamón de Parma y hojaldre. Horneado hasta obtener un dorado perfecto.",
            chef = "Gordon Ramsay",
            foto = "http://10.0.2.2/recetas/7.jpg"
        ),
        RecetaMock(
            id = 8,
            nombre = "Fish and Chips Gourmet",
            descripcion = "Pescado blanco fresco rebozado en una masa ligera de cerveza, servido con patatas fritas gruesas y puré de guisantes a la menta.",
            chef = "Gordon Ramsay",
            foto = "http://10.0.2.2/recetas/8.jpg",
            favorita = true
        ),

        RecetaMock(
            id = 9,
            nombre = "Milhojas de Anguila Ahumada",
            descripcion = "Plato insignia que combina capas de anguila ahumada, foie gras y manzana ácida caramelizada. Una explosión de texturas y sabores.",
            chef = "Martín Berasategui",
            foto = "http://10.0.2.2/recetas/9.jpg"
        ),
        RecetaMock(
            id = 10,
            nombre = "Ensalada Tibia de Tuétanos",
            descripcion = "Composición vegetal con marisco y una emulsión ligera. Representa la elegancia y la técnica precisa de la cocina de Berasategui.",
            chef = "Martín Berasategui",
            foto = "http://10.0.2.2/recetas/10.jpg"
        )
    )

    suspend fun insert(receta: RecetaMock) = coroutineScope {
        recetas.add(receta)
    }

    suspend fun update(receta: RecetaMock) = coroutineScope {
        val idx = recetas.indexOfFirst { it.id == receta.id }
        if (idx != -1) recetas[idx] = receta
    }

    suspend fun delete(id: Int) = coroutineScope {
        recetas.removeIf { it.id == id }
    }

    suspend fun count(): Int = coroutineScope { recetas.size }

    suspend fun getTodas(): List<RecetaMock> = coroutineScope { recetas }

    suspend fun getReceta(id : Int): RecetaMock = coroutineScope {
        recetas.find { it.id == id }!!
    }

    suspend fun getRecetasPorChef(chef: String): List<RecetaMock> = coroutineScope {
        recetas.filter { it.chef == chef }
    }

    suspend fun getRecetasFavoritas(): List<RecetaMock> = coroutineScope {
        recetas.filter { it.favorita }
    }

    suspend fun getChefs(): List<String> = coroutineScope {
        recetas.map { it.chef }.distinct().sorted()
    }
}