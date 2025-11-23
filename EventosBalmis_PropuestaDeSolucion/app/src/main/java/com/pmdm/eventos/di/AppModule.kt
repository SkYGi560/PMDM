package com.pmdm.eventos.di

import com.pmdm.eventos.data.mocks.EventoDaoMock
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEventoDaoMock(): EventoDaoMock {
        return EventoDaoMock()
    }
}