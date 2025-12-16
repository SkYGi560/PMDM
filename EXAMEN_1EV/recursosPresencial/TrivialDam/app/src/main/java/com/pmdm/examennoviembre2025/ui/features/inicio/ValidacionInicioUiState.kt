package com.pmdm.examennoviembre2025.ui.features.inicio

import com.github.pmdmiesbalmis.components.validacion.Validacion

data class ValidacionInicioUiState(
    val validacionNombre: Validacion = object : Validacion {},
    val validacionCategoria: Validacion = object : Validacion {},
    val validacionPreguntasSeleccionadas: Validacion = object : Validacion {}
): Validacion {
    override val hayError: Boolean
        get() = validacionNombre.hayError || validacionCategoria.hayError || validacionPreguntasSeleccionadas.hayError
    override val mensajeError: String?
        get() = if(validacionNombre.hayError) validacionNombre.mensajeError
        else if(validacionCategoria.hayError) validacionCategoria.mensajeError
        else if(validacionPreguntasSeleccionadas.hayError) validacionPreguntasSeleccionadas.mensajeError
        else ""
}