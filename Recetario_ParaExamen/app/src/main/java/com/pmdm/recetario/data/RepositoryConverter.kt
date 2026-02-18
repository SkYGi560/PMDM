package com.pmdm.recetario.data

import com.pmdm.recetario.data.mocks.RecetaMock
import com.pmdm.recetario.data.room.recetas.RecetaEntity
import com.pmdm.recetario.model.Receta


fun RecetaEntity.aReceta(): Receta = Receta(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    chef = chef,
    foto = foto,
    favorita = favorita
)

fun Receta.aRecetaEntity(): RecetaEntity = RecetaEntity(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    chef = chef,
    foto = foto,
    favorita = favorita
)

fun RecetaMock.aReceta(): Receta = Receta(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    chef = chef,
    foto = foto,
    favorita = favorita
)

