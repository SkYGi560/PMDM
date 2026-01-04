package com.pmdm.agenda.di

import com.pmdm.agenda.data.ContactoRepository
import com.pmdm.agenda.data.mocks.contacto.ContactoDaoMock
import com.pmdm.agenda.features.ValidadorContacto
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
        contactoDaoMock: ContactoDaoMock
    ) : ContactoRepository = ContactoRepository(contactoDaoMock)

    @Provides
    @Singleton
    fun provideValidadorContacto() : ValidadorContacto = ValidadorContacto()
}