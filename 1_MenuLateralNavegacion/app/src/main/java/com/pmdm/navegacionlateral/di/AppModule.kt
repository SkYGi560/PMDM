package com.pmdm.navegacionlateral.di

import com.pmdm.navegacionlateral.data.mocks.ContactoDaoMock
import com.pmdm.navegacionlateral.data.mocks.ContactoRepository
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
    fun provideContactoDaoMock(): ContactoDaoMock = ContactoDaoMock()

    @Provides
    @Singleton
    fun provideUsuarioRepository(
        contactoDaoMock: ContactoDaoMock
    ): ContactoRepository =
       ContactoRepository(contactoDaoMock)
}