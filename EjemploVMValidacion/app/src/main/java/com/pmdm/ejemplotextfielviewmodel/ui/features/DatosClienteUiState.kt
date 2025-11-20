package com.pmdm.ejemplotextfielviewmodel.ui.features


data class DatosClienteUiState(
    val id: Int = 0,
    val nombre: String = "",
    val apellidos: String = "",
    val dni: String = "",
    val telefono: String = "",
    val mostrarDialogo: Boolean = false,
    val textoDialogo: String = ""
)
