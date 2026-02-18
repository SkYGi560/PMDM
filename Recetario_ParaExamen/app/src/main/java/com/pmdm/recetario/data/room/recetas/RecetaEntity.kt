package com.pmdm.recetario.data.room.recetas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recetas")
data class RecetaEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "descripcion")
    val descripcion: String,
    @ColumnInfo(name = "chef")
    val chef: String,
    @ColumnInfo(name = "foto")
    val foto: String?,
    @ColumnInfo(name = "favorita")
    val favorita: Boolean
)