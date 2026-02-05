package com.pmdm.ejemploroom.data.room.cliente

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Direccion(
    val calle: String?="",
    val ciudad: String?="",
    val pais: String?="",
    @ColumnInfo(name = "codigo_postal")
    val codigoPostal: String?=""
)

@Entity(tableName = "clientes")
data class ClienteEntity(
    @PrimaryKey
    @ColumnInfo(name = "dni")
    val dni: String="",
    @ColumnInfo(name = "nombre")
    val nombre: String="",
    @ColumnInfo(name = "apellidos")
    val apellidos: String="",
    // Marcamos como embebe @Embedded el campo a descomponer en la tabla
    @Embedded val direccion: Direccion?=Direccion())