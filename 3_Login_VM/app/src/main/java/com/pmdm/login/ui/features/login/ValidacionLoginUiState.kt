package com.pmdm.login.ui.features.login

import com.github.pmdmiesbalmis.components.validacion.Validacion

data class ValidacionLoginUiState(
    val validacionLogin: Validacion = object : Validacion {},
    val validacionPassword: Validacion = object : Validacion {},
) : Validacion {
    override val hayError: Boolean
        get() = validacionLogin.hayError || validacionPassword.hayError
    override val mensajeError: String?
        get() = "Error autenticandose"
}