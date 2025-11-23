package com.pmdm.eventos.ui.features.presentacion_eventos

import com.github.pmdmiesbalmis.components.validacion.Validacion

data class DialogoUiState(
    val nombre: String = "",
    val correo: String = "",
    val dialogoVisible : Boolean = false,
    val dialogoComprobacionVisible : Boolean = false,
    val validacionNombre : Validacion = object: Validacion {},
    val validacionCorreo : Validacion = object: Validacion {},

    )