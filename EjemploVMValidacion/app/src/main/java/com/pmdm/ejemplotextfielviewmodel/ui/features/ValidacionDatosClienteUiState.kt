package com.pmdm.ejemplotextfielviewmodel.ui.features

import com.github.pmdmiesbalmis.components.validacion.Validacion


data class ValidacionDatosClienteUiState(
    val validacionNombre: Validacion = object : Validacion {},
    val validacionTelefono: Validacion = object : Validacion {},
    val validacioDNI: Validacion = object : Validacion {}
) : Validacion {
    override val hayError: Boolean
        get() = validacionNombre.hayError || validacioDNI.hayError
                || validacionTelefono.hayError
    override val mensajeError: String?
        get() = if (validacionNombre.hayError) validacionNombre.mensajeError
        else if (validacionTelefono.hayError) validacionTelefono.mensajeError
        else if (validacioDNI.hayError) validacioDNI.mensajeError
        else ""
}
