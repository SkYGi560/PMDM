package com.pmdm.login.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pmdm.login.ui.features.LoginScreen
import com.pmdm.login.ui.features.login.LoginViewModel

import com.pmdm.login.ui.theme.LoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm: LoginViewModel by viewModels()

        setContent {
            LoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(
                        loginUiState = vm.loginUiState,
                        validacionLoginUiState = vm.validacionLoginUiState,
                        onLoginEvent = vm::onLoginEvent
                    )
                }
            }
        }
    }
}

