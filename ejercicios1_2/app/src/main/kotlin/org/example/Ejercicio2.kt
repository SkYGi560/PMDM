package org.example

data class Serie(
    val codigo: Int,
    val titulo: String,
    val numeroTemporadas: Int,
    val numeroCapitulosTemporada: Int,
    val temporada: Int,
    val capitulo: Int,
    val acabada: Boolean,
    val genero: Genero,
    val creador: String
){
    public enum class Genero(val codigo: Int,val ejemplos: String){
        INDEFINIDO(0,"Indefinidos"),
        THRILLER(11,  "Black Mirror, Breaking Bad, Sons of Anarchy, Casa de Papel..."),
        ANIMACION(21, "Rick y Morty, Bojack Horseman, Mr. Pickles..."),
        FICCION(31, "The 100, Orphan Black, The Leftovers, Stranger Things..."),
        DRAMA (41, "Homeland, The Good Wife, House of Cards..."),
        COMEDIA(51, "Arrested Development, The Bid Bang Theory, Orange Is The New Black, Sex Educ"),
        TERROR(61, "American Horror Story, The Outsider, The Haunting of Hill House...");

        override fun toString() = "$name como [$ejemplos]"
        }
    }
fun main(){
    val serie1 = Serie(
        11,
        "Black mirror",
        10,
        10,
        2,
        10,
        false,
        Serie.Genero.THRILLER,
        "Pepe Williams"
    )
    println(serie1)
    val serie2 = serie1.copy(acabada = true)
    println(serie2)
}

