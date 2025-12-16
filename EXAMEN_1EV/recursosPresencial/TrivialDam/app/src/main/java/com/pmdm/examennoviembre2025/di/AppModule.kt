package com.pmdm.examennoviembre2025.di

import com.pmdm.examennoviembre2025.data.PreguntaRepository
import com.pmdm.examennoviembre2025.data.mocks.pregunta.PreguntaDaoMock
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePreguntaDaoMock(): PreguntaDaoMock = PreguntaDaoMock()

    @Provides
    @Singleton
    fun providePreguntaRepository(preguntaDaoMock: PreguntaDaoMock
    ): PreguntaRepository =
        PreguntaRepository(preguntaDaoMock)
}