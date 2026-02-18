package com.pmdm.recetario

import android.app.Application
import com.pmdm.recetario.data.aReceta
import com.pmdm.recetario.data.aRecetaEntity
import com.pmdm.recetario.data.mocks.RecetaDaoMock
import com.pmdm.recetario.data.room.recetas.RecetaDao
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class RecetarioApplication: Application () {
    @Inject
    lateinit var daoMock: RecetaDaoMock
    @Inject
    lateinit var daoEntity: RecetaDao

    override fun onCreate() {
        super.onCreate()

        runBlocking {
            if (daoEntity.count() == 0)
                daoMock.getTodas().forEach { daoEntity.insert(it.aReceta().aRecetaEntity()) }
        }
    }
}