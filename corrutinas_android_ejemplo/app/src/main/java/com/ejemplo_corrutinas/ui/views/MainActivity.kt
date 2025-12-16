package com.ejemplo_corrutinas.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.ejemplo_corrutinas.ui.features.seguimientocarrera.SeguimientoCarreraScreen
import com.ejemplo_corrutinas.ui.features.seguimientocarrera.SeguimientoCarreraViewModel
import com.ejemplo_corrutinas.ui.theme.EjemploCorrutinasTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    // Definimos el ViewModel como propiedad delegada y así usarla en
    // los métodos del ciclo de vida de la actividad
    private val vm by viewModels<SeguimientoCarreraViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploCorrutinasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SeguimientoCarreraScreen(
                        corredor1 = vm.corredor1,
                        corredor2 = vm.corredor2,
                        enCarrera = vm.enCarrera,
                        onSeguimientoCarreraEvent = vm::onSeguimientoCarreraEvent
                    )
                }
            }
        }
    }

    // Al pausar la actividad por cualquier motivo, paramos la carrera
    // a través del ViewModel que es quine gestiona las corrutinas de la misma
    // Ten en cuenta que el Accance del ViewModel sigue vivo aunque la actividad
    // esté pausada y por tanto los corredores seguirán corriendo
    override fun onPause() {
        super.onPause()
        vm.pararDeCorrer()
    }
}
