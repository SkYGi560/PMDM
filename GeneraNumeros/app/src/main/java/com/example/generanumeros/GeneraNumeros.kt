package com.example.generanumeros

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun generaNumeros (): Deferred<List<Int>> = GlobalScope.async {
    val lista = mutableListOf<Int>()

    for (i in 1..(10..20).random()) {
        delay((200L..500L).random())
        lista.add((1..100).random())
    }

    lista
}

suspend fun recogeNumeros(): Deferred<List<Int>> =
    GlobalScope.async {
        val lista = mutableListOf<Int>()
        var numero  = -1

        do {
            numero = readLine()?.toIntOrNull() ?: continue

            if (numero != 0) {
                lista.add(numero)
            }
        } while (numero != 0)

        lista
    }

fun muestraListas(
    listaAleatoria: List<Int>,
    listaUsuario: List<Int>
) {
    println("Lista Generada Aleatoria")
    println(
        listaAleatoria.joinToString(" - ") { it.toString() }
    )

    println()
    println("Tu lista con las 'coincidencias'")
    println(
        listaUsuario.joinToString(" - ") { numero ->
            if (numero in listaAleatoria) "'$numero'" else numero.toString()
        }
    )
}

fun main() = runBlocking {
    println("Iniciando la generación de números....")
    println("Introduce números hasta 100, 0 para parar:")

    val jobAleatoria = generaNumeros()
    val jobUsuario = recogeNumeros()

    val listaAleatoria = jobAleatoria.await()
    val listaUsuario = jobUsuario.await()

    println()
    muestraListas(listaAleatoria, listaUsuario)
}