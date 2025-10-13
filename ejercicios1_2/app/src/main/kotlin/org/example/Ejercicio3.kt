package org.example

sealed interface SeriesEvent {
    object ListaSeries : SeriesEvent
    object ListaSeriesAcabadas : SeriesEvent
    data class ListaSeriesGenero(val genero: String) : SeriesEvent
    data class MarcaSerieAcabada(val codigo: Int) : SeriesEvent
    data class  MarcaSeguimiento(val codigo: Int,
                                 val numTemporada: Int,
                                 val numCapitulo: Int) : SeriesEvent
}
fun onSeriesEvent(seriesEvent: SeriesEvent){
    when(seriesEvent){
        is SeriesEvent.ListaSeries -> println("Lista de series")
        is SeriesEvent.ListaSeriesAcabadas -> println("Lista series acabadas")
        is SeriesEvent.ListaSeriesGenero -> println("Lista series por el genero con codigo ${seriesEvent.genero}")
        is SeriesEvent.MarcaSerieAcabada -> println("Se ha marcado como acabada la serie con codigo ${seriesEvent.codigo}")
        is SeriesEvent.MarcaSeguimiento -> println("como vista hasta la temporada - ${seriesEvent.numTemporada} y capítulo ${seriesEvent.numCapitulo}")
    }
}
fun accionSeleccion(opcion : String){
    val numOpcion = opcion.toInt()
    when(numOpcion){
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
            val numTemporada = readln().toInt()
            println("Introduce el capítulo de la serie a marcar")
            val numCapitulo = readln().toInt()
            onSeriesEvent(SeriesEvent.MarcaSeguimiento(codigo,numTemporada,numCapitulo))
        }
    }
}

fun main(){
    do
    {
        println("1. Listar todas mis series")
        println("2. Listar mis series acabadas")
        println("3. Listar mis series por género")
        println("4. Marcar serie como acabada")
        println("5. Marcar seguimiento de la serie")
        println("6. Salir")
        val opcion=readln()
        accionSeleccion(opcion)
    } while(opcion!="6")

}