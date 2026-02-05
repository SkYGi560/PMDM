package com.pmdm.tienda.models

import java.time.LocalDate


data class Pedido(
    val id: Int=0,
    val dniCliente: String="",
    val fecha: LocalDate=LocalDate.now(),

)
