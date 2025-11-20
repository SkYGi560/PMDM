package com.pmdm.login.ui.features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.ejercicio5.data.UsuarioRepository

class LoginViewModel : ViewModel() {

    private val repository: UsuarioRepository = UsuarioRepository()
    var loginUiState by mutableStateOf(LoginUiState())
        private set
    var validacionLoginUiState : ValidacionLoginUiState by mutableStateOf(ValidacionLoginUiState())
        private set
    val validadorLoginUi : ValidadorLoginUi by mutableStateOf(ValidadorLoginUi())

    fun onLoginEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLoginChanged -> {
                loginUiState = loginUiState.copy(loginState = event.login)
                validacionLoginUiState =
                    validacionLoginUiState.copy(
                        validacionLogin = validadorLoginUi.validadorLogin
                                                          .valida(event.login)
                    )
            }
            is LoginEvent.OnPasswordChanged -> {
                loginUiState = loginUiState.copy(passwordState = event.password)
                validacionLoginUiState =
                    validacionLoginUiState.copy(
                        validacionPassword = validadorLoginUi.validadorPassword
                            .valida(event.password)
                    )
            }
            is LoginEvent.OnLogin -> {
                validacionLoginUiState = validadorLoginUi.valida(loginUiState) as ValidacionLoginUiState

                if (!validacionLoginUiState.hayError) {
                    validaUsuario()
                }
            }
        }
    }

    private fun validaUsuario() {
        val user = repository.get(loginUiState.loginState)
        loginUiState = loginUiState.copy(
            logeado = user?.password == loginUiState.passwordState
        )
    }

}