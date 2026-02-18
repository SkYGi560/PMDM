package com.pmdm.recetario.data.room.recetas

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pmdm.recetario.model.Receta

@Dao
interface RecetaDao {

    @Insert
    suspend fun insert(receta: RecetaEntity)

    @Update
    suspend fun update(receta: RecetaEntity)

    @Query("DELETE FROM recetas WHERE id = :id")
    suspend fun delete(id : Int)

    @Query("SELECT COUNT(*) FROM recetas")
    suspend fun count(): Int

    @Query("SELECT * FROM recetas")
    suspend fun getTodas(): List<RecetaEntity>

    @Query("SELECT * FROM recetas WHERE id IN (:id)")
    suspend fun getPorId(id : Int): RecetaEntity

    @Query("SELECT * FROM recetas WHERE chef IN (:chef)")
    suspend fun getPorChef(chef : String): List<RecetaEntity>

    @Query("SELECT * FROM recetas WHERE favorita = 1")
    suspend fun getFavoritas(): List<RecetaEntity>

    @Query("SELECT DISTINCT chef FROM recetas ORDER BY chef ASC")
    suspend fun getChefs(): List<String>
}