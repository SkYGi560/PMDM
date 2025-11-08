package com.pmdm.login.ui.features.login

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.composables.CheckboxWithLabel
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldEmail
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldPassword
import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.pmdm.login.ui.features.login.components.CircularImageFromResource
import com.pmdm.login.ui.features.login.components.TextNewAccount
import com.pmdm.login.ui.theme.LoginTheme
import java.nio.file.WatchEvent

@Composable
fun UsuarioPassword(
    emailState: String,
    passwordState: String,
    checkboxState:Boolean,
    validacionStateEmail: Validacion,
    validacionStatePassword: Validacion,
    onValueChangeEmail: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
    onValueChangeCheckBox: (Boolean) -> Unit,
    onButtonClick: () -> Unit
    ){
    Column(
        modifier = Modifier.then(Modifier.fillMaxWidth()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextFieldEmail(modifier = Modifier.then(Modifier.fillMaxWidth()), emailState = emailState, validacionState = validacionStateEmail, onValueChange = onValueChangeEmail, label = "Login")
        OutlinedTextFieldPassword(
            modifier = Modifier.then(Modifier.fillMaxWidth()),
            passwordState = passwordState,
            validacionState = validacionStatePassword,
            onValueChange = onValueChangePassword,
            label = "Password")
        CheckboxWithLabel(label = "Recordarme", checkedState = checkboxState, onStateChange = onValueChangeCheckBox)
        Button(onClick = onButtonClick, modifier = Modifier.fillMaxWidth()) { Text("Login") }
    }
}

@Composable
fun LoginScreen(
    imagen: Int,
    emailState: String,
    passwordState: String,
    checkboxState:Boolean,
    validacionStateEmail: Validacion,
    validacionStatePassword: Validacion,
    onValueChangeEmail: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
    onValueChangeCheckBox: (Boolean) -> Unit,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularImageFromResource(imagen,"imagen de login")
        UsuarioPassword(
            emailState,
            passwordState,
            checkboxState,
            validacionStateEmail,
            validacionStatePassword,
            onValueChangeEmail,
            onValueChangePassword,
            onValueChangeCheckBox,
            onButtonClick
        )
        TextButton(onClick = {}) { Text("Olvidaste Password?") }
        Text("ó")
        Row {
            IconButton({}) {
                Image(painter = painterResource(com.pmdm.login.R.drawable.img), contentDescription = "Imagen de facebook")
            }
            IconButton({}) {
                Image(painter = painterResource(com.pmdm.login.R.drawable.img_1), contentDescription = "Imagen de email")
            }
            }
        TextNewAccount({})
    }
}

@Preview(showBackground = true)
@Composable
fun UsuarioPasswordPreview(){
    val imagen = com.pmdm.login.R.drawable.login
    var emailState by rememberSaveable { mutableStateOf("") }
    var passwordState by rememberSaveable { mutableStateOf("") }
    var checkBoxState by rememberSaveable { mutableStateOf(false) }
    var validacionLogin by remember { mutableStateOf(object : Validacion {} as Validacion) }
    var validacionPassword by remember { mutableStateOf(object : Validacion {} as Validacion) }
    val context = LocalContext.current


    LoginTheme {
        LoginScreen(
            imagen = imagen,
            emailState = emailState,
            passwordState = passwordState,
            checkboxState = checkBoxState,
            validacionStateEmail = validacionLogin,
            validacionStatePassword = validacionPassword,
            onValueChangeEmail = { nuevoValorEmail -> emailState = nuevoValorEmail },
            onValueChangePassword = { nuevoValorPassword -> passwordState = nuevoValorPassword},
            onValueChangeCheckBox = { nuevoValorCheckBox -> checkBoxState = nuevoValorCheckBox },
            onButtonClick = {
                var msg = ""
                msg = if (Patterns.EMAIL_ADDRESS.matcher(emailState).matches() && passwordState.length >= 8)
                    "Entrando a la APP"
                else
                    "Comprueba los datos"

                Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
            }

        )
    }
}