package com.pmdm.mosaico.ui.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pmdm.mosaico.R


@Composable
fun MosaicoScreen(
    mosaicoUIState: MosaicoUIState,
    onMosaicoEvent: (MosaicoEvent) -> Unit
) {

    DibujaMosaico(
        mosaico = mosaicoUIState.imagenes,
        colorBorde = MaterialTheme.colorScheme.inversePrimary,
        posicionSlider = mosaicoUIState.posicionSlider,
        onMosaicoEvent = onMosaicoEvent
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DibujaMosaico(
    mosaico: List<Imagen>,
    colorBorde: Color,
    posicionSlider: Float,
    onMosaicoEvent: (MosaicoEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.inversePrimary)
            .verticalScroll(rememberScrollState())
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Slider(
            modifier = Modifier
                .size(170.dp, 3.dp)
                .align(Alignment.CenterHorizontally),
            value = posicionSlider,
            onValueChange = {
                onMosaicoEvent(MosaicoEvent.onChangePosicionSlider(it))
            },
            valueRange = 1f..8f
        )
        Spacer(modifier = Modifier.height(20.dp))

        FlowRow(
            modifier = Modifier,
            maxItemsInEachRow = posicionSlider.toInt()
        ) {
            for (i in mosaico)
                RectanguloImagen(
                    urlImagen = i.url,
                    colorBorde = colorBorde,
                )
        }

    }
}

@Composable
fun RectanguloImagen(urlImagen: String, colorBorde: Color) {

    Box {
        //Solo para que carge la preview , que no funciona con AsyncImage
        if (LocalInspectionMode.current) {
            Image(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .border(
                        width = 1.dp, color = colorBorde
                    ),
                painter = painterResource(R.drawable.fila_1_columna_2),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .border(
                        width = 1.dp, color = colorBorde
                    ),
                model = urlImagen,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
fun MosaicoPreview() {
    var mosaicoUIState by remember {
        mutableStateOf(
            MosaicoUIState(
                imagenes = List<Imagen>(size=60,init={Imagen()}),
                posicionSlider = 4f
            )
        )
    }

    MosaicoScreen(
        mosaicoUIState = mosaicoUIState,
        onMosaicoEvent = {})
}
