package com.example.ejercicios_1_2_1

object GestionClaves {
    private var contraseña: MutableMap<String, String> = mutableMapOf()

    class ClavesException(mensaje: String) : Exception(mensaje)

    fun añadeLogin() {

        println("Introduce el usuario: ")
        val usuario = readln()
        println("Introduce la contraseña: ")
        val pwd = readln()

        if (contraseña.keys.contains(usuario))
            throw ClavesException("El usuario ya existe")
        else
            contraseña[usuario] = pwd
    }

    fun modificaLogin() {
        println("Dime un user que ya este creado : ")
        val user = readln()
        if (contraseña.keys.contains(user)) {
            println("Dime la nueva contraseña")
            contraseña[user] = readln()
        } else
            throw ClavesException("El usuario no existe")
    }

    fun eliminaLogin() {
        println("Dime el usuario que quieres eliminar")
        val user = readln()
        if (contraseña.keys.contains(user)) {
            println("Dime la contraseña: ")
            if (contraseña[user] == readln()) {
                contraseña.remove(user)
                println("Usuario eliminado")
            } else
                throw ClavesException("La contraseña introducida no es correcta")
        } else
            throw ClavesException("El usuario introducido no existe")
    }

    fun muestraUsers() = contraseña.forEach { u, p ->
        println("El usuario $u tiene la contraseña $p")
    }
}


fun main() {
    var entrada = ""

    do {
        println("1. Añade login")
        println("2. Modifica password")
        println("3. Elimina login")
        println("4. Salir")
        entrada = readln()
        when (entrada) {
            "1" -> GestionClaves.añadeLogin()
            "2" -> GestionClaves.modificaLogin()
            "3" -> GestionClaves.eliminaLogin()
            "4" -> println("Saliendo...")
            else -> throw GestionClaves.ClavesException("Entrada no valida")
        }
    } while (!entrada.equals("4"))
    GestionClaves.muestraUsers()
}