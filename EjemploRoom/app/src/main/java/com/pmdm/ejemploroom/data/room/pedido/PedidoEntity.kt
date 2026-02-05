package com.pmdm.ejemploroom.data.room.pedido

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.pmdm.ejemploroom.data.room.cliente.ClienteEntity
import java.time.LocalDate

@Entity(tableName = "pedidos",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ClienteEntity::class,
            parentColumns = arrayOf("dni"),
            childColumns = arrayOf("dni_cliente"),
            onDelete = CASCADE)
    )
)
data class PedidoEntity(
    // El id será autogenerado insertando un 0.
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "dni_cliente")
    val dniCliente: String,
    // Indicamos que siempre debe tener una fecha.
    // Esta en la DB se guardará como un Int.
    @NonNull
    @ColumnInfo(name = "fecha", typeAffinity = ColumnInfo.INTEGER)
    val fecha: LocalDate
)

