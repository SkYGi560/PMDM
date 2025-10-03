package com.example.ejercicios_1_2_1

sealed interface SeriesEvent {
    object ListaSeries : SeriesEvent
    object ListaSeriesAcabadas : SeriesEvent
    data class ListaSeriesGenero(val genero: String) : SeriesEvent
    data class MarcaSerieAcabada(val codigo: Int) : SeriesEvent
    data class MarcaSeguimiento(
        val codigo: Int,
        val temporada: Int,
        val capitulo: Int
    ) : SeriesEvent
}

fun onSeriesEvent(seriesEvent: SeriesEvent) {
    when (seriesEvent) {
        is SeriesEvent.ListaSeries -> lista.forEach { s -> println(s) }
        is SeriesEvent.ListaSeriesAcabadas -> lista.filter { s -> s.acabada }.forEach { serie -> println(serie) }
        is SeriesEvent.ListaSeriesGenero -> lista.filter { s -> s.genero == Serie.Genero.valueOf(seriesEvent.genero.uppercase()) }.forEach { serie -> println(serie) }
        is SeriesEvent.MarcaSerieAcabada -> lista.filter { serie -> serie.codigo == seriesEvent.codigo }.forEach { serie ->
            val pos = lista.indexOf(serie)
            lista[pos] = serie.copy(acabada = true)
            println("Se ha cambiado a acabada la serie ${serie.titulo}")
        }
        is SeriesEvent.MarcaSeguimiento -> lista.filter { serie -> serie.codigo == seriesEvent.codigo }.forEach { serie ->
            val pos = lista.indexOf(serie)
            lista[pos] = serie.copy(temporada = seriesEvent.temporada, capitulo = seriesEvent.capitulo)
            println("La serie con codigo: ${seriesEvent.codigo}se ha marcado como vista hasta la temporada - ${seriesEvent.temporada} y capítulo ${seriesEvent.capitulo}")
        }
    }
}

fun accionSeleccion(opcion: String) {
    val numOpcion = opcion.toInt()
    when (numOpcion) {
        1 -> onSeriesEvent(SeriesEvent.ListaSeries)
        2 -> onSeriesEvent(SeriesEvent.ListaSeriesAcabadas)
        3 -> {
            println("Dime el gnero por el que quieres que ordene")
            val genero = readln()
            onSeriesEvent(SeriesEvent.ListaSeriesGenero(genero))
        }

        4 -> {
            println("Dime el codigo de la serie que has acabado")
            val codigo = readln().toInt()
            onSeriesEvent(SeriesEvent.MarcaSerieAcabada(codigo))
        }

        5 -> {
            println("Introduce el código de la serie de la que quieres marcar su posición")
            val codigo = readln().toInt()
            println("Introduce la temporada de la serie a marcar")
            val temporada = readln().toInt()
            println("Introduce el capítulo de la serie a marcar")
            val capitulo = readln().toInt()
            onSeriesEvent(SeriesEvent.MarcaSeguimiento(codigo, temporada, capitulo))
        }
    }
}

var lista = mutableListOf<Serie>()
fun main() {
    lista.addAll(
        listOf(
            Serie(
                1,
                "Breaking Bad",
                5,
                13,
                5, 13, true, Serie.Genero.THRILLER, "Vince Gilligan"),
            Serie(
                2,
                "Stranger Things",
                4,
                9,
                3,
                5,
                false,
                Serie.Genero.FICCION,
                "Duffer Brothers"),
            Serie(
                3,
                "Rick y Morty",
                7,
                10,
                4,
                3,
                false,
                Serie.Genero.ANIMACION,
                "Justin Roiland"),
            Serie(
                4,
                "The Good Wife",
                7,
                22,
                7,
                22,
                true,
                Serie.Genero.DRAMA,
                "Michelle King"),
            Serie(
                5,
                "The Big Bang Theory",
                12,
                24,
                12,
                24,
                true,
                Serie.Genero.COMEDIA,
                "Chuck Lorre"
            ),
            Serie(
                6,
                "The Haunting of Hill House",
                1,
                10,
                1,
                10,
                true,
                Serie.Genero.TERROR,
                "Mike Flanagan"
            ),
            Serie(
                7,
                "Orphan Black",
                5,
                10,
                3,
                7,
                false,
                Serie.Genero.FICCION,
                "Graeme Manson"),
            Serie(
                8,
                "House of Cards",
                6,
                13,
                6,
                13,
                true,
                Serie.Genero.DRAMA,
                "Beau Willimon"),
            Serie(
                9,
                "Bojack Horseman",
                6,
                12,
                5,
                8,
                false,
                Serie.Genero.ANIMACION,
                "Raphael Bob-Waksberg"
            ),
            Serie(
                10,
                "Black Mirror",
                6,
                6,
                6,
                6,
                true,
                Serie.Genero.THRILLER,
                "Charlie Brooker")
        )
    )
    do {
        println("1. Listar todas mis series")
        println("2. Listar mis series acabadas")
        println("3. Listar mis series por género")
        println("4. Marcar serie como acabada")
        println("5. Marcar seguimiento de la serie")
        println("6. Salir")
        val opcion = readln()
        accionSeleccion(opcion)
    } while (opcion != "6")

}