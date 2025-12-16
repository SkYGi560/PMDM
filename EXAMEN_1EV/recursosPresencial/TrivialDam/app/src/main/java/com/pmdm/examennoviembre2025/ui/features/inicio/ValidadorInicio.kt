package com.pmdm.examennoviembre2025.ui.features.inicio

import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.github.pmdmiesbalmis.components.validacion.Validador
import com.github.pmdmiesbalmis.components.validacion.ValidadorCompuesto
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorTextoNoVacio

class ValidadorInicio: Validador<InicioUiState> {
    var validadorNombre = ValidadorTextoNoVacio("El nombre no puede estar vacío")


    override fun valida(datos: InicioUiState): ValidacionInicioUiState {
        val validacionNombre = validadorNombre.valida(datos.jugadorNombre)

        return ValidacionInicioUiState(
            validacionNombre = validacionNombre
        )
    }

}