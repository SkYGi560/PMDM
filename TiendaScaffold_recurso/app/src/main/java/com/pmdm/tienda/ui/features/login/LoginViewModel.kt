package com.pmdm.tienda.ui.features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pmdm.tienda.data.UsuarioRepository
import com.pmdm.tienda.models.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel  @Inject constructor(
    private val usuarioRepository: UsuarioRepository,
    private val validadorLogin: ValidadorLogin)
    : ViewModel() {

    var usuarioUiState by mutableStateOf(LoginUiState())
        private set
    var validacionLoginUiState by mutableStateOf(ValidacionLoginUiState())
        private set

    fun onLoginEvent(loginEvent: LoginEvent) {
        when (loginEvent) {
            is LoginEvent.LoginChanged -> {
                usuarioUiState = usuarioUiState.copy(
                    login = loginEvent.login
                )
                validacionLoginUiState = validacionLoginUiState.copy(
                    validacionLogin =validadorLogin.validadorLogin.valida(loginEvent.login)
                )
            }

            is LoginEvent.PasswordChanged -> {
                usuarioUiState = usuarioUiState.copy(
                    password = loginEvent.password
                    )
                validacionLoginUiState = validacionLoginUiState.copy(
                    validacionPassword = validadorLogin.validadorPassword.valida(loginEvent.password)
                )
            }

            is LoginEvent.OnClickLogearse -> {
                validacionLoginUiState = validadorLogin.valida(usuarioUiState)
                if (!validacionLoginUiState.hayError)
                    usuarioUiState = usuarioUiState.copy(
                        estaLogeado = logearse()
                    )
            }
        }
    }

    fun logearse(): Boolean {
        val usuario = usuarioUiState.toUsuario()
        usuarioRepository.get().forEach {
            if (usuario.login == it.login && usuario.password == it.password) return true
        }
        return false
    }

    fun LoginUiState.toUsuario() = Usuario(this.login, this.password)
}