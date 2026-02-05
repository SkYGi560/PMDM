package com.pmdm.agenda.data.room.contacto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactoDao {
    @Query("SELECT * FROM contactos")
    suspend fun get(): List<ContactoEntity>

    @Query("SELECT * FROM contactos WHERE id = :id")
    suspend fun get(id: Int): ContactoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contacto: ContactoEntity)

    @Update
    suspend fun update(contacto: ContactoEntity)

    @Query("DELETE FROM contactos WHERE id = :id")
    suspend fun delete(id: Int)
    @Query("SELECT COUNT(*) FROM contactos")
    suspend fun count(): Int

}