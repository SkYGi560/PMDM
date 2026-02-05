package com.pmdm.agenda.ui.features

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.pmdm.agenda.models.Contacto
import java.util.EnumSet
import com.pmdm.agenda.R

@Composable
private fun Contacto.Categorias.CatergoriaUiStateIconPaninter() : Painter = when(this) {
    Contacto.Categorias.Amigos -> painterResource(R.drawable.sports_esports_24px)
    Contacto.Categorias.Trabajo -> painterResource(R.drawable.work_24px)
    Contacto.Categorias.Familia -> painterResource(R.drawable.family_restroom_24px)
    Contacto.Categorias.Emergencias -> painterResource(R.drawable.medical_services_24px)
}

@Composable
fun CatergoriaUiState.AmigosIcon() = Contacto.Categorias.Amigos.CatergoriaUiStateIconPaninter()
@Composable
fun CatergoriaUiState.TrabajoIcon() = Contacto.Categorias.Trabajo.CatergoriaUiStateIconPaninter()
@Composable
fun CatergoriaUiState.FamiliaIcon() = Contacto.Categorias.Familia.CatergoriaUiStateIconPaninter()
@Composable
fun CatergoriaUiState.EmergenciasIcon() = Contacto.Categorias.Emergencias.CatergoriaUiStateIconPaninter()

data class CatergoriaUiState(
    val amigos: Boolean = false,
    val trabajo: Boolean = false,
    val familia: Boolean = false,
    val emergencias: Boolean = false,
)

fun CatergoriaUiState.toEnum() = EnumSet.noneOf(Contacto.Categorias::class.java).apply {
    if (amigos) add(Contacto.Categorias.Amigos)
    if (trabajo) add(Contacto.Categorias.Trabajo)
    if (familia) add(Contacto.Categorias.Familia)
    if (emergencias) add(Contacto.Categorias.Emergencias)
}