package com.pmdm.cronometro.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.pmdm.cronometro.ui.features.CronometroScreen
import com.pmdm.cronometro.ui.theme.CronometroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CronometroTheme {
                CronometroScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}