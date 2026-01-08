package com.pmdm.scaffold.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pmdm.scaffold.ui.theme.EjemplosScaffoldTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemplosScaffoldTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                }
            }
        }
    }
}
