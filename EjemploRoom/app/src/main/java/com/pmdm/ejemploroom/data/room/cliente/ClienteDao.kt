package com.pmdm.ejemploroom.data.room.cliente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface ClienteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente : ClienteEntity)

    @Delete
    suspend fun delete(cliente : ClienteEntity)

    @Update
    suspend fun update(cliente : ClienteEntity)

    // Si no pasamos la entidad y lo que tenemos
    // es la PK deberemos hacer una consulta con @Query
    // pasando la PK como parámetro.
    @Query("DELETE FROM clientes WHERE dni = :dni")
    suspend fun deleteByDni(dni: String)

    @Query("SELECT * FROM clientes")
    suspend fun get(): List<ClienteEntity>

    @Query("SELECT * FROM clientes WHERE dni IN (:dni)")
    suspend fun getFromDni(dni : String): ClienteEntity

    @Query("SELECT COUNT(*) FROM clientes")
    suspend fun count(): Int

    @Transaction
    // Fíjate que no indicamos la relación en la consulta ya está definida en el objeto a recuperar.
    @Query("SELECT * FROM clientes")
    // Fíjate que devuelve una lista de objetos ClienteConPedidos
    suspend fun getPedidos(): List<ClienteConPedidosEntity>
}