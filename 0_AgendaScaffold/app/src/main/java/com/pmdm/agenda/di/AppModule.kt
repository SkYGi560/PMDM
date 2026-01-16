package com.pmdm.agenda.di

import com.pmdm.agenda.data.ContactoRepository
import com.pmdm.agenda.data.mocks.contacto.ContactoDaoMock
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
    fun provideContactoDaoMock() : ContactoDaoMock = ContactoDaoMock()

    @Provides
    @Singleton
    fun provideContactoRepository(
        // Este es inyectado por provideContactoDaoMock() y
        // resuelto automáticamente por Hilt
        contactoDaoMock: ContactoDaoMock
    ) : ContactoRepository = ContactoRepository(contactoDaoMock)
}