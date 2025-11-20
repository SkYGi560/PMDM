package com.pmdm.login.ui.features.login

import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.github.pmdmiesbalmis.components.validacion.Validador
import com.github.pmdmiesbalmis.components.validacion.validadores.*

class ValidadorLoginUi : Validador<LoginUiState> {

    val validadorLogin = ValidadorTextoNoVacio("El login no puede estar vacío")
    val validadorPassword = ValidadorPassword()

    override fun valida(datos: LoginUiState): Validacion {
        val validacionLogin = validadorLogin.valida(datos.loginState)
        val validacionPassword = validadorPassword.valida(datos.passwordState)
        return ValidacionLoginUiState(validacionLogin, validacionPassword)
    }

}