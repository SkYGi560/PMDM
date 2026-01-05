package com.pmdm.leefichero.ui.features

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pmdm.leefichero.utilities.device.registroSelectorDeFicheroConGetContent

@Composable
fun LectorTxtScreen(vm: LectorViewModel = viewModel()) {

    val launcher = registroSelectorDeFicheroConGetContent { uri ->
        vm.cargarDesdeUri(uri)
    }

    val scrollState = rememberScrollState()
    LaunchedEffect(vm.textoBuilder.length) {
        if (vm.cargando) scrollState.scrollTo(0)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { launcher.launch("*/*") },
            enabled = !vm.cargando,
            modifier = Modifier.align(alignment = androidx.compose.ui.Alignment.CenterHorizontally)
        ) { Text("Cargar") }

        Spacer(Modifier.height(12.dp))

        if (vm.cargando) {
            LinearProgressIndicator(
                progress = { vm.progreso },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
        }

        // 👇 Caja con altura "restante": SCROLL garantizado
        Surface(
            tonalElevation = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = vm.textoBuilder.toString(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .verticalScroll(
                        state = scrollState,
                        reverseScrolling = true // requisito del ejercicio
                    )
            )
        }
    }
}