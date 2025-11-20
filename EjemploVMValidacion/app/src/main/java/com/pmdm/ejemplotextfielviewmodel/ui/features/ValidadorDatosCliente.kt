package com.pmdm.ejemplotextfielviewmodel.ui.features

import com.github.pmdmiesbalmis.components.validacion.Validador
import com.github.pmdmiesbalmis.components.validacion.ValidadorCompuesto
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorDni
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorTelefono
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorTextoNoVacio

class ValidadorDatosCliente : Validador<DatosClienteUiState> {
    var validadorNombre = ValidadorCompuesto<String>().add(
        ValidadorTextoNoVacio("El nombre no puede estar vacio")
    )
    var validadorTelefono = ValidadorCompuesto<String>().add(
        ValidadorTextoNoVacio("El teléfono no puede estar vacio")
    ).add(ValidadorTelefono("El teléfono debe tener formáto , mínimo 9 dígitos"))

    var validadorDni = ValidadorCompuesto<String>().add(
        ValidadorTextoNoVacio("El dni no puede estar vacio")
    ).add(ValidadorDni("El dni debe ser correcto"))


    override fun valida(datos: DatosClienteUiState): ValidacionDatosClienteUiState {
        val validacionNombre = validadorNombre.valida(datos.nombre)
        val validacionTelefono = validadorTelefono.valida(datos.telefono)
        val validacionDni = validadorDni.valida(datos.dni)
        return ValidacionDatosClienteUiState(
            validacionNombre = validacionNombre,
            validacionTelefono = validacionTelefono,
            validacioDNI = validacionDni
        )
    }
}