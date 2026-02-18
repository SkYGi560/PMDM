package com.pmdm.recetario.di

import android.content.Context
import com.pmdm.recetario.data.RecetarioRepository
import com.pmdm.recetario.data.mocks.RecetaDaoMock
import com.pmdm.recetario.data.room.RecetasDB
import com.pmdm.recetario.data.room.recetas.RecetaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRecetaDatabase(
        @ApplicationContext context: Context
    ): RecetasDB = RecetasDB.getDatabase(context)

    @Provides
    @Singleton
    fun provideRecetasDao(
        recetasDB: RecetasDB
    ): RecetaDao = recetasDB.recetaDao()

    @Provides
    @Singleton
    fun provideRecetaDaoMock() : RecetaDaoMock = RecetaDaoMock()

    @Provides
    @Singleton
    fun provideRecetarioRepository(
        recetaDao: RecetaDao
    ) : RecetarioRepository = RecetarioRepository(recetaDao)

}