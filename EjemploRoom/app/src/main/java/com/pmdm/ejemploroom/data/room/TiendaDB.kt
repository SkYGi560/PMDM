package com.pmdm.ejemploroom.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pmdm.ejemploroom.data.room.cliente.ClienteDao
import com.pmdm.ejemploroom.data.room.cliente.ClienteEntity
import com.pmdm.ejemploroom.data.room.pedido.PedidoDao
import com.pmdm.ejemploroom.data.room.pedido.PedidoEntity

@TypeConverters(RoomConverters::class)
@Database(
    entities = [ClienteEntity::class, PedidoEntity::class],
    version = 3
)
abstract class TiendaDB: RoomDatabase() {
    abstract fun clienteDao() : ClienteDao
    abstract fun pedidoDao() : PedidoDao
    companion object {
        fun getDatabase(
            context: Context
        ) = Room.databaseBuilder(
            context,
            TiendaDB::class.java, "tienda"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }}

