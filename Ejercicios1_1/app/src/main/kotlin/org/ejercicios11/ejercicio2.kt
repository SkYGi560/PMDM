package org.ejercicios11

import kotlin.random.Random

fun main() {
    var equi = 0
    var iso = 0
    var esc = 0
    for (i in 0 until 10)
    {
        val res =resuelveTriangulo(generaLados())
        println("El triangulo $i es $res")
        if(res == "EQUILATERO")
            equi++
        if(res == "ISOSCELES")
            iso++
        if(res == "ESCALENO")
            esc++
    }
    println("En total se han generado")
    println("$equi triangulos equilateros")
    println("$iso triangulos isosceles")
    println("$esc triangulos escalenos")

}

fun generaLados() : Triple<Int,Int,Int> =
    Triple(Random.nextInt(1,10),Random.nextInt(1,10),Random.nextInt(1,10))

fun resuelveTriangulo(lados: Triple<Int,Int,Int>) : String {

    val res = when {
        lados.first == lados.second && lados.first == lados.third ->  "EQUILATERO"
        lados.first == lados.second || lados.first == lados.third->  "ISOSCELES"
        else -> "ESCALENO"
    }
    return res
}