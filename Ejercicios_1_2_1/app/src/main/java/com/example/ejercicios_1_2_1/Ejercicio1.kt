package com.example.ejercicios_1_2_1

val provincias: List<String> = listOf(
    "Álava", "Albacete", "Alicante", "Almería", "Asturias", "Ávila",
    "Badajoz", "Baleares", "Barcelona", "Burgos", "Cáceres", "Cádiz",
    "Cantabria", "Castellón", "Ciudad Real", "Córdoba", "Cuenca", "Girona",
    "Granada", "Guadalajara", "Guipúzcoa", "Huelva", "Huesca", "Jaén",
    "La Coruña", "La Rioja", "Las Palmas", "León", "Lleida", "Lugo",
    "Madrid", "Málaga", "Murcia", "Navarra", "Ourense", "Palencia",
    "Pontevedra", "Salamanca", "Santa Cruz de Tenerife", "Segovia",
    "Sevilla", "Soria", "Tarragona", "Teruel", "Toledo", "Valencia",
    "Valladolid", "Vizcaya", "Zamora", "Zaragoza", "Ceuta", "Melilla"
)
var pagas = Array(12){}
var grupos = Array(5){}
data class Articulos(val nombre:String,val cantidad: Int)
var carrito: MutableList<String> = mutableListOf()
val meses: List<String> = listOf(
    "Enero",
    "Febrero",
    "Marzo",
    "Abril",
    "Mayo",
    "Junio",
    "Julio",
    "Agosto",
    "Septiembre",
    "Octubre",
    "Noviembre",
    "Diciembre"
)
var login : MutableMap<String,String> = mutableMapOf()
var series: MutableList<Serie> = mutableListOf()
val relacionMesesDias = mapOf<String, Int>()
var menu: Pair<Int,String> = Pair(1,"")
val palabrasClave = listOf<String>()


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