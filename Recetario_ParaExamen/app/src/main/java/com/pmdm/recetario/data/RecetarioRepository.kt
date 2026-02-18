package com.pmdm.recetario.data

import com.pmdm.recetario.data.mocks.RecetaDaoMock
import com.pmdm.recetario.data.room.recetas.RecetaDao
import com.pmdm.recetario.data.room.recetas.RecetaEntity
import com.pmdm.recetario.model.Receta
import javax.inject.Inject

class RecetarioRepository @Inject constructor(
    val dao: RecetaDao
) {

    suspend fun insert(receta: Receta) = dao.insert(receta = receta.aRecetaEntity())

    suspend fun update(receta: Receta) = dao.update(receta = receta.aRecetaEntity())

    suspend fun delete(id : Int) = dao.delete(id)

    suspend fun count(): Int = dao.count()

    suspend fun getTodas(): List<Receta> = dao.getTodas().map { it.aReceta() }

    suspend fun getPorId(id : Int): Receta = dao.getPorId(id).aReceta()

    suspend fun getPorChef(chef : String): List<Receta> = dao.getPorChef(chef).map { it.aReceta() }

    suspend fun getFavoritas(): List<Receta> = dao.getFavoritas().map { it.aReceta() }

    suspend fun getChefs(): List<String> = dao.getChefs()
}