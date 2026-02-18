package com.pmdm.recetario.ui.features.seleccionchef

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SeleccionChefDialog(
    listaChefsState: List<String>,
    onSeleccinarNombreChef: (nombre: String) -> Unit,
    onCancelarSeleccionNombre: () -> Unit
) {
    var chefSeleccionadoTemp by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = {
            onCancelarSeleccionNombre()
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Restaurant,
                contentDescription = null
            )
        },
        title = {
            Text(
                text = "Selecciona un Chef",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Desliza hacia arriba para ver más:",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                HorizontalDivider()

                LazyColumn(
                    modifier = Modifier
                        .heightIn(max = 300.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(listaChefsState) { chef ->
                        ChefItemRow(
                            nombre = chef,
                            isSelected = chef == chefSeleccionadoTemp,
                            onSelect = { chefSeleccionadoTemp = it }
                        )
                    }
                }

                HorizontalDivider()
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    chefSeleccionadoTemp?.let { onSeleccinarNombreChef(it) }
                },
                enabled = chefSeleccionadoTemp != null
            ) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelarSeleccionNombre) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun ChefItemRow(
    nombre: String,
    isSelected: Boolean,
    onSelect: (String) -> Unit
) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        Color.Transparent
    }

    val contentColor = if (isSelected) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable { onSelect(nombre) }
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelect(nombre) },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = nombre,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = contentColor,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SeleccionChefDialogPreview() {
    val chefs = remember {
        listOf(
            "Gordon Ramsay",
            "Massimo Bottura",
            "Jamie Oliver",
            "Dabiz Muñoz",
            "Joan Roca",
            "Ferran Adrià"
        )
    }

    MaterialTheme {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Fondo de la App")

                SeleccionChefDialog(
                    listaChefsState =chefs,
                    onSeleccinarNombreChef = { println("Seleccionado: $it") },
                    onCancelarSeleccionNombre = { println("Cancelado") }
                )
            }
        }
    }
}