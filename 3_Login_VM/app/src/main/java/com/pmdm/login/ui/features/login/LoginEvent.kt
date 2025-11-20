package com.pmdm.login.ui.features.login

sealed interface LoginEvent {
    data class OnLoginChanged(val login: String) : LoginEvent
    data class OnPasswordChanged(val password: String) : LoginEvent
    object OnLogin : LoginEvent
}