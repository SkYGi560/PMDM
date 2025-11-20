package com.pmdm.login.ui.features.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.github.pmdmiesbalmis.components.ui.composables.CheckboxWithLabel
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldEmail
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldPassword

import com.github.pmdmiesbalmis.components.validacion.Validacion


@Composable
fun UsuarioPassword(
    modifier: Modifier,
    loginState: String,
    validacionLogin: Validacion,
    passwordState: String,
    validacionPassword: Validacion,
    recordarmeState: Boolean,
    onValueChangeLogin: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
    onCheckedChanged: (Boolean) -> Unit,
    onClickLogearse: () -> Unit
) {
    OutlinedTextFieldEmail(
        modifier = modifier,
        label = "Login",
        emailState = loginState,
        validacionState = validacionLogin,
        onValueChange = onValueChangeLogin
    )

    OutlinedTextFieldPassword(
        modifier = modifier,
        label = "Password",
        passwordState = passwordState,
        validacionState = validacionPassword,
        onValueChange = onValueChangePassword
    )

    CheckboxWithLabel (
        label = "Recordarme",
        checkedState = recordarmeState,
        onStateChange =  onCheckedChanged
    )

    Button(
        onClick = onClickLogearse,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("Login")
    }
}
