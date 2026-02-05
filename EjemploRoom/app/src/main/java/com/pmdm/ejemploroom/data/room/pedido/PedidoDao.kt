package com.pmdm.ejemploroom.data.room.pedido

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PedidoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pedido : PedidoEntity)

    @Delete
    suspend fun delete(pedido : PedidoEntity)

    @Update
    suspend fun update(pedido : PedidoEntity)

    @Query("SELECT * FROM pedidos")
    suspend fun get(): List<PedidoEntity>


    @Query("SELECT COUNT(*) FROM pedidos")
    suspend fun count(): Int
}