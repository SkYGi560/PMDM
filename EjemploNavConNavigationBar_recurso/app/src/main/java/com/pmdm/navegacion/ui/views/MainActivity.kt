package com.pmdm.navegacion.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pmdm.navegacion.ui.features.EjemploNavDentroDeUnScaffold
import com.pmdm.navegacion.ui.theme.EjemploNavegacionTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploNavegacionTheme {
                EjemploNavegacionTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        EjemploNavDentroDeUnScaffold()
                    }
                }
            }
        }
    }
}
