@file:OptIn(ExperimentalMaterial3Api::class)

package com.pmdm.cronometro.ui.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pmdm.cronometro.ui.theme.CronometroTheme
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private fun formateaSegundos(segundosEntrada: Int): String {
    val hora = (segundosEntrada / 3600).toString()
    val minutos = (segundosEntrada % 3600 / 60).toString()
    val segundos = (segundosEntrada % 60).toString()
    val stringBuilder = StringBuilder()
    stringBuilder.append(if (hora.toInt() < 10) "0$hora" else hora)
    stringBuilder.append(":")
    stringBuilder.append(if (minutos.toInt() < 10) "0$minutos" else minutos)
    stringBuilder.append(":")
    stringBuilder.append(if (segundos.toInt() < 10) "0$segundos" else segundos)
    return stringBuilder.toString()
}

@Composable
fun CronometroScreen(modifier: Modifier = Modifier) {
    var cuenta by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    var job by remember { mutableStateOf<Job?>(null) }

    fun onStart() {
        if (job?.isActive != true) {
            job = scope.launch {
                while (true) {
                    delay(1000)
                    cuenta++
                }
            }
        }
    }

    fun onPause() {
        if (job?.isActive == true) {
            job.let { it?.cancel() }
        } else {
            job = scope.launch {
                while (true) {
                    delay(1000)
                    cuenta++
                }
            }
        }
    }

    fun onReset() {
        job.let { it?.cancel() }
        cuenta = 0
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = formateaSegundos(cuenta),
            color = Color.Blue,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedIconButton(onClick = { onStart() }, modifier = Modifier.width(60.dp).padding(2.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Outlined.PlayArrow,
                        contentDescription = "icono empieza"
                    )
                    Text("Start", fontSize = 10.sp, lineHeight = 10.sp)
                }
            }
            OutlinedIconButton(onClick = { onPause() }, modifier = Modifier.width(60.dp).padding(2.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Outlined.Star, contentDescription = "icono pausa")
                    Text("Stop", fontSize = 10.sp, lineHeight = 10.sp)
                }
            }
            OutlinedIconButton(onClick = { onReset() }, modifier = Modifier.width(60.dp).padding(2.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Outlined.Refresh, contentDescription = "icono reset")
                    Text("Reset", fontSize = 10.sp, lineHeight = 10.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CronometroPreview() {
    CronometroTheme {
        CronometroScreen()
    }
}
