package com.pmdm.agenda.di

import android.content.Context
import com.pmdm.agenda.data.ContactoRepository
import com.pmdm.agenda.data.mocks.contacto.ContactoDaoMock
import com.pmdm.agenda.data.room.AgendaDb
import com.pmdm.agenda.data.room.ContactoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAgendaDatabase(
        @ApplicationContext context: Context
    ) : AgendaDb = AgendaDb.getDatabase(context)

    @Provides
    @Singleton
    fun provideContactoDao(
        // La Bd es inyectada por provideAgendaDatabase() y
        // resuelto automáticamente por Hilt
        db: AgendaDb
    ) : ContactoDao = db.contactoDao()

    @Provides
    @Singleton
    fun provideContactoDaoMock() : ContactoDaoMock = ContactoDaoMock()

    @Provides
    @Singleton
    fun provideContactoRepository(
        contactoDao: ContactoDao
    ) : ContactoRepository = ContactoRepository(contactoDao)
}