package com.pmdm.examennoviembre2025.data.mocks.pregunta

import kotlin.random.Random

class PreguntaDaoMock {

    //0-CienciasNaturales, 1-ArteLiteratura, 2-Geografía, 3-História, 4-Entretenimiento
    val preguntas = listOf(
        PreguntaMock(
            pregunta = "¿Qué planeta es conocido como el \"planeta rojo\"?",
            respuestas = listOf("Venus", "Júpiter", "Marte", "Saturno"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/CN_Imagen1.jpg",
            tipoPregunta = 0
        ),
        PreguntaMock(
            pregunta = "¿Qué tipo de animal es la ballena?",
            respuestas = listOf("Pez", "Reptil", "Mamífero", "Anfibio"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/CN_Imagen2.jpg",
            tipoPregunta = 0
        ),
        PreguntaMock(
            pregunta = "¿Qué gas es esencial para la fotosíntesis de las plantas?",
            respuestas = listOf("Oxígeno", "Nitrógeno", "Dióxido de carbono", "Helio"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/CN_Imagen3.jpg",
            tipoPregunta = 0
        ),
        PreguntaMock(
            pregunta = "¿Cuál es el órgano más grande del cuerpo humano?",
            respuestas = listOf("El hígado", "El corazón", "La piel", "El intestino"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/CN_Imagen4.jpg",
            tipoPregunta = 0
        ),
        PreguntaMock(
            pregunta = "¿Qué fenómeno natural se mide con la escala de Richter?",
            respuestas = listOf("Huracanes", "Terremotos", "Tornados", "Inundaciones"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/CN_Imagen5.jpg",
            tipoPregunta = 0
        ),
        PreguntaMock(
            pregunta = "¿Qué planeta tiene anillos visibles desde la Tierra?",
            respuestas = listOf("Júpiter", "Urano", "Neptuno", "Saturno"),
            opcionCorrecta = 3,
            imagenUrl = "http://10.0.2.2/imagenes_juego/CN_Imagen6.jpg",
            tipoPregunta = 0
        ),
        PreguntaMock(
            pregunta = "¿Qué animal es este (considerado el más rápido del mundo)?",
            respuestas = listOf("León", "Guepardo", "Halcón peregrino", "Antílope"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/CN_Imagen7.jpg",
            tipoPregunta = 0
        ),
        PreguntaMock(
            pregunta = "¿Qué partícula subatómica tiene carga positiva?",
            respuestas = listOf("Electrón", "Neutrón", "Protón", "Fotón"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/CN_Imagen8.jpg",
            tipoPregunta = 0
        ),
        PreguntaMock(
            pregunta = "¿Qué proceso utilizan las plantas para absorber agua a través de sus raíces?",
            respuestas = listOf("Transpiración", "Fotosíntesis", "Ósmosis", "Evaporación"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/CN_Imagen9.jpg",
            tipoPregunta = 0
        ),
        PreguntaMock(
            pregunta = "¿Qué capa de la atmósfera nos protege de los rayos ultravioleta del sol?",
            respuestas = listOf("Troposfera", "Estratosfera", "Mesosfera", "Capa de ozono"),
            opcionCorrecta = 3,
            imagenUrl = "http://10.0.2.2/imagenes_juego/CN_Imagen10.jpg",
            tipoPregunta = 0
        ),
        PreguntaMock(
            pregunta = "¿Quién pintó 'La noche estrellada'?",
            respuestas = listOf(
                "Pablo Picasso",
                "Vincent van Gogh",
                "Salvador Dalí",
                "Claude Monet"
            ),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/AR_Imagen1.jpg",
            tipoPregunta = 1
        ),
        PreguntaMock(
            pregunta = "¿Quién escribió 'Cien años de soledad'?",
            respuestas = listOf(
                "Mario Vargas Llosa",
                "Julio Cortázar",
                "Gabriel García Márquez",
                "Isabel Allende"
            ),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/AR_Imagen2.jpg",
            tipoPregunta = 1
        ),
        PreguntaMock(
            pregunta = "¿En qué movimiento artístico se enmarcó Picasso con su obra 'Guernica'?",
            respuestas = listOf("Impresionismo", "Surrealismo", "Cubismo", "Expresionismo"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/AR_Imagen3.jpg",
            tipoPregunta = 1
        ),
        PreguntaMock(
            pregunta = "¿Quién es el autor de 'Don Quijote de la Mancha'?",
            respuestas = listOf(
                "Miguel de Cervantes",
                "Lope de Vega",
                "Francisco de Quevedo",
                "Calderón de la Barca"
            ),
            opcionCorrecta = 0,
            imagenUrl = "http://10.0.2.2/imagenes_juego/AR_Imagen4.jpg",
            tipoPregunta = 1
        ),
        PreguntaMock(
            pregunta = "¿Qué escritor inglés es conocido por obras como 'Romeo y Julieta' y 'Hamlet'?",
            respuestas = listOf(
                "Charles Dickens",
                "William Shakespeare",
                "Oscar Wilde",
                "Jane Austen"
            ),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/AR_Imagen5.jpg",
            tipoPregunta = 1
        ),
        PreguntaMock(
            pregunta = "¿Qué pintor español es conocido por sus relojes derretidos en 'La persistencia de la memoria'?",
            respuestas = listOf("Pablo Picasso", "Joan Miró", "Salvador Dalí", "Francisco de Goya"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/AR_Imagen6.jpg",
            tipoPregunta = 1
        ),
        PreguntaMock(
            pregunta = "¿Quién escribió '1984', novela que introduce el concepto del 'Gran Hermano'?",
            respuestas = listOf("Aldous Huxley", "George Orwell", "Ray Bradbury", "H.G. Wells"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/AR_Imagen7.jpg",
            tipoPregunta = 1
        ),
        PreguntaMock(
            pregunta = "¿Qué obra arquitectónica es esta?",
            respuestas = listOf(
                "El Coliseo Romano",
                "El Partenón",
                "La Sagrada Familia",
                "Notre Dame"
            ),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/AR_Imagen8.jpg",
            tipoPregunta = 1
        ),
        PreguntaMock(
            pregunta = "¿Quién pintó la Capilla Sixtina en el Vaticano?",
            respuestas = listOf("Leonardo da Vinci", "Rafael Sanzio", "Miguel Ángel", "Donatello"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/AR_Imagen9.jpg",
            tipoPregunta = 1
        ),
        PreguntaMock(
            pregunta = "¿Qué poeta chileno ganó el Premio Nobel de Literatura en 1971?",
            respuestas = listOf(
                "Pablo Neruda",
                "Gabriela Mistral",
                "Octavio Paz",
                "Jorge Luis Borges"
            ),
            opcionCorrecta = 0,
            imagenUrl = "http://10.0.2.2/imagenes_juego/AR_Imagen10.jpg",
            tipoPregunta = 1
        ),
        PreguntaMock(
            pregunta = "¿Cuál es el río más largo del mundo?",
            respuestas = listOf("Nilo", "Amazonas", "Misisipi", "Yangtsé"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/GE_Imagen1.jpg",
            tipoPregunta = 2
        ),
        PreguntaMock(
            pregunta = "¿Qué montaña es la más alta del mundo?",
            respuestas = listOf("K2", "Monte Everest", "Aconcagua", "Kilimanjaro"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/GE_Imagen2.jpg",
            tipoPregunta = 2
        ),
        PreguntaMock(
            pregunta = "¿Cuál es el país más grande del mundo por superficie?",
            respuestas = listOf("China", "Estados Unidos", "Rusia", "Canadá"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/GE_Imagen3.jpg",
            tipoPregunta = 2
        ),
        PreguntaMock(
            pregunta = "¿Qué desierto es el más grande del mundo?",
            respuestas = listOf(
                "Desierto del Sáhara",
                "Desierto de Arabia",
                "Desierto de Gobi",
                "Antártida"
            ),
            opcionCorrecta = 3,
            imagenUrl = "http://10.0.2.2/imagenes_juego/GE_Imagen4.jpg",
            tipoPregunta = 2
        ),
        PreguntaMock(
            pregunta = "¿Qué océano es el más grande del planeta?",
            respuestas = listOf("Atlántico", "Índico", "Pacífico", "Ártico"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/GE_Imagen5.jpg",
            tipoPregunta = 2
        ),
        PreguntaMock(
            pregunta = "¿Qué famosa catarata se encuentra entre Argentina y Brasil?",
            respuestas = listOf(
                "Cataratas del Niágara",
                "Cataratas Victoria",
                "Cataratas del Iguazú",
                "Salto del Ángel"
            ),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/GE_Imagen6.jpg",
            tipoPregunta = 2

        ),
        PreguntaMock(
            pregunta = "¿Qué cadena montañosa separa Europa de Asia?",
            respuestas = listOf("Alpes", "Himalaya", "Montes Urales", "Cárpatos"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/GE_Imagen7.jpg",
            tipoPregunta = 2

        ),
        PreguntaMock(
            pregunta = "¿Cuál es la capital de Australia?",
            respuestas = listOf("Sídney", "Melbourne", "Canberra", "Perth"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/GE_Imagen8.jpg",
            tipoPregunta = 2

        ),
        PreguntaMock(
            pregunta = "¿Qué país tiene forma de 'bota'?",
            respuestas = listOf("Grecia", "España", "Italia", "Francia"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/GE_Imagen9.jpg",
            tipoPregunta = 2

        ),
        PreguntaMock(
            pregunta = "¿Qué estrecho separa África de Europa?",
            respuestas = listOf(
                "Estrecho de Bering",
                "Estrecho de Gibraltar",
                "Estrecho de Magallanes",
                "Estrecho de Hormuz"
            ),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/GE_Imagen10.jpg",
            tipoPregunta = 2

        ),
        PreguntaMock(
            pregunta = "¿En qué año comenzó la Segunda Guerra Mundial?",
            respuestas = listOf("1914", "1939", "1941", "1945"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/HI_Imagen1.jpg",
            tipoPregunta = 3
        ),
        PreguntaMock(
            pregunta = "¿Qué civilización construyó Machu Picchu?",
            respuestas = listOf("Azteca", "Maya", "Inca", "Olmeca"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/HI_Imagen2.jpg",
            tipoPregunta = 3
        ),
        PreguntaMock(
            pregunta = "¿Quién descubrió América en 1492?",
            respuestas = listOf(
                "Vasco da Gama",
                "Cristóbal Colón",
                "Fernando de Magallanes",
                "Américo Vespucio"
            ),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/HI_Imagen3.jpg",
            tipoPregunta = 3
        ),
        PreguntaMock(
            pregunta = "¿En qué siglo llegó Napoleón Bonaparte al poder en Francia?",
            respuestas = listOf("Siglo XVII", "Siglo XVIII", "Siglo XIX", "Siglo XX"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/HI_Imagen4.jpg",
            tipoPregunta = 3
        ),
        PreguntaMock(
            pregunta = "¿Qué imperio era gobernado por Julio César?",
            respuestas = listOf(
                "Imperio Bizantino",
                "Imperio Romano",
                "Imperio Otomano",
                "Imperio Persa"
            ),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/HI_Imagen5.jpg",
            tipoPregunta = 3
        ),
        PreguntaMock(
            pregunta = "¿En qué año cayó el Muro de Berlín?",
            respuestas = listOf("1987", "1989", "1991", "1993"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/HI_Imagen6.jpg",
            tipoPregunta = 3
        ),
        PreguntaMock(
            pregunta = "¿Qué famosa reina de Egipto tuvo romances con Julio César y Marco Antonio?",
            respuestas = listOf("Nefertiti", "Cleopatra", "Hatshepsut", "Nefertari"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/HI_Imagen7.jpg",
            tipoPregunta = 3
        ),
        PreguntaMock(
            pregunta = "¿En qué año se firmó la Declaración de Independencia de Estados Unidos?",
            respuestas = listOf("1776", "1789", "1799", "1812"),
            opcionCorrecta = 0,
            imagenUrl = "http://10.0.2.2/imagenes_juego/HI_Imagen8.jpg",
            tipoPregunta = 3
        ),
        PreguntaMock(
            pregunta = "¿Qué civilización antigua desarrolló la democracia en Atenas?",
            respuestas = listOf("Persia", "Roma", "Grecia Antigua", "Egipto"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/HI_Imagen9.jpg",
            tipoPregunta = 3
        ),
        PreguntaMock(
            pregunta = "¿Quién fue el primer hombre en pisar la Luna?",
            respuestas = listOf("Buzz Aldrin", "Neil Armstrong", "Yuri Gagarin", "John Glenn"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/HI_Imagen10.jpg",
            tipoPregunta = 3
        ),
        PreguntaMock(
            pregunta = "¿Qué actor interpretó a Jack Dawson en la película 'Titanic'?",
            respuestas = listOf("Brad Pitt", "Johnny Depp", "Leonardo DiCaprio", "Tom Cruise"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/EN_Imagen1.jpg",
            tipoPregunta = 4
        ),
        PreguntaMock(
            pregunta = "¿Qué famosa saga de películas incluye 'El despertar de la fuerza'?",
            respuestas = listOf("Star Trek", "Star Wars", "Matrix", "El Señor de los Anillos"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/EN_Imagen2.jpg",
            tipoPregunta = 4
        ),
        PreguntaMock(
            pregunta = "¿Qué cantante es conocida como 'La Reina del Pop'?",
            respuestas = listOf("Beyoncé", "Lady Gaga", "Madonna", "Rihanna"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/EN_Imagen3.jpg",
            tipoPregunta = 4
        ),
        PreguntaMock(
            pregunta = "¿Qué serie de televisión trata sobre el juego de tronos en los Siete Reinos?",
            respuestas = listOf("The Crown", "Vikingos", "Juego de Tronos", "The Witcher"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/EN_Imagen4.jpg",
            tipoPregunta = 4
        ),
        PreguntaMock(
            pregunta = "¿Qué película de animación cuenta la historia de Miguel y su familia en el Día de Muertos?",
            respuestas = listOf("Moana", "Coco", "Encanto", "Frozen"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/EN_Imagen5.jpg",
            tipoPregunta = 4
        ),
        PreguntaMock(
            pregunta = "¿Qué grupo musical británico es famoso por canciones como 'Bohemian Rhapsody'?",
            respuestas = listOf("The Beatles", "Queen", "Rolling Stones", "Led Zeppelin"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/EN_Imagen6.jpg",
            tipoPregunta = 4
        ),
        PreguntaMock(
            pregunta = "¿Qué superhéroe de Marvel es conocido como 'Dios del Trueno'?",
            respuestas = listOf("Iron Man", "Thor", "Hulk", "Capitán América"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/EN_Imagen7.jpg",
            tipoPregunta = 4
        ),
        PreguntaMock(
            pregunta = "¿Qué famoso videojuego de construcción tiene como logo un personaje con cabeza de creeper?",
            respuestas = listOf("Fortnite", "Roblox", "Minecraft", "Terraria"),
            opcionCorrecta = 2,
            imagenUrl = "http://10.0.2.2/imagenes_juego/EN_Imagen8.jpg",
            tipoPregunta = 4
        ),
        PreguntaMock(
            pregunta = "¿Qué actor interpretó a Iron Man en el Universo Cinematográfico de Marvel?",
            respuestas = listOf(
                "Chris Evans",
                "Robert Downey Jr.",
                "Chris Hemsworth",
                "Mark Ruffalo"
            ),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/EN_Imagen9.jpg",
            tipoPregunta = 4
        ),
        PreguntaMock(
            pregunta = "¿Qué cantante colombiana es conocida por canciones como 'Hips Don't Lie'?",
            respuestas = listOf("Karol G", "Shakira", "Maluma", "J Balvin"),
            opcionCorrecta = 1,
            imagenUrl = "http://10.0.2.2/imagenes_juego/EN_Imagen10.jpg",
            tipoPregunta = 4
        )
    )

    fun getPreguntasAleatoriasPor(temas: List<Int>, nivel: Int): List<PreguntaMock> {
        var preguntasMock = mutableListOf<PreguntaMock>()
        var listaIndicesGenerados = mutableListOf<Int>()

        while (temas.size>0 && preguntasMock.size < nivel) {
            val generado = Random.nextInt(0, preguntas.size)
            if (!listaIndicesGenerados.contains(generado) && temas.contains(preguntas[generado].tipoPregunta)) {
                preguntasMock.add(preguntas[generado])
                listaIndicesGenerados.add(generado)
            }
        }
        return preguntasMock;
    }
}