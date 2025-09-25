package org.ejercicios11

fun main(){
    println(permiteEntrada(leeEdad()))
}

fun leeEdad() : Short{
    var entrada = ""
    println("Dime tu edad: ")
    entrada = readln()
    if(!entrada.sonSoloDigitos())
        fail("Has introducido una edad no válida")

    return entrada.toShort()
}

fun fail(men: String) : Unit = throw IllegalArgumentException(men)

fun String.sonSoloDigitos() : Boolean {
    var es = true
    for (i in 0 .. this.length-1)
        if(!this[i].isDigit()){
            es = false
            break
        }
    return es
}

fun permiteEntrada(edad: Short) : String {
    return if(edad > 17)
        "Puedes pasar!!"
    else
        "No puedes pasar!!"
}