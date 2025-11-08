package com.pmdm.dialogos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pmdm.dialogos.ui.composables.NoseqPreview
import com.pmdm.dialogos.ui.theme.Dialogos2425Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Dialogos2425Theme {
                NoseqPreview()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Dialogos2425Theme {
        NoseqPreview()
    }
}