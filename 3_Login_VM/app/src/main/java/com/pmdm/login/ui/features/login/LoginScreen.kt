package com.pmdm.login.ui.features

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.pmdm.login.R
import com.pmdm.login.ui.features.login.LoginEvent
import com.pmdm.login.ui.features.login.LoginUiState
import com.pmdm.login.ui.features.login.ValidacionLoginUiState
import com.pmdm.login.ui.features.login.ValidadorLoginUi
import com.pmdm.login.ui.features.login.components.CircularImageFromResource
import com.pmdm.login.ui.features.login.components.TextNewAccount
import com.pmdm.login.ui.features.login.components.UsuarioPassword
import com.pmdm.login.ui.theme.LoginTheme
import com.pmdm.login.ui.theme.Purple40


@Composable

fun LoginScreen(
    loginUiState: LoginUiState,
    validacionLoginUiState: ValidacionLoginUiState,
    onLoginEvent: (LoginEvent) -> Unit
) {

    if (loginUiState.logeado) {
        Text(text = "Bienvenido ${loginUiState.loginState}")
    }
    else
    {
        val contexto = LocalContext.current

        var recordarmeState by remember { mutableStateOf(false) }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            CircularImageFromResource(
                idImageResource = R.drawable.login, contentDescription = "Imagen Login"
            )

            UsuarioPassword(
                modifier = Modifier.fillMaxWidth(),
                loginState = loginUiState.loginState,
                passwordState = loginUiState.passwordState,
                validacionLogin = validacionLoginUiState.validacionLogin,
                validacionPassword = validacionLoginUiState.validacionPassword,
                recordarmeState = recordarmeState,
                onValueChangeLogin = { onLoginEvent(LoginEvent.OnLoginChanged(it)) },
                onValueChangePassword = { onLoginEvent(LoginEvent.OnPasswordChanged(it)) },
                onCheckedChanged = { recordarmeState = it },
                onClickLogearse = {
                    onLoginEvent(LoginEvent.OnLogin)
                })
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Text(
                "Olvidaste Password?",
                fontSize = 15.sp,
                fontStyle = FontStyle.Italic,
                color = Purple40
            )
            Text("ó")
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Facebook",
                    alignment = Alignment.Center,
                    modifier = Modifier.size(35.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.gmail),
                    contentDescription = "Gmail",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(36.dp)
                        .padding(3.dp)

                )
            }
            TextNewAccount(onClick = {
                Toast.makeText(contexto, "Nueva Cuenta", Toast.LENGTH_LONG).show()
            })
            validacionLoginUiState.mensajeError?.let {
                Text(
                    text = it,
                )
            }
        }

    }
}

@Preview(
    showBackground = true, device = "spec:parent=pixel_5,orientation=landscape",
    name = "LandScape"
)

@Preview(
    showBackground = true,
    device = "spec:parent=pixel_5",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun LoginText() {

    LoginTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            color = MaterialTheme.colorScheme.background
        ) {

//            LoginScreen(

        }
    }
}