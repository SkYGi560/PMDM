package com.pmdm.agenda.di

import android.content.Context
import com.pmdm.agenda.data.ContactoRepository
import com.pmdm.agenda.data.mocks.contacto.ContactoDaoMock
import com.pmdm.agenda.data.room.AgendaDb
import com.pmdm.agenda.data.room.contacto.ContactoDao
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
        db: AgendaDb
    ) : ContactoDao = db.contactoDao()
    // En el proveedor del repositorio sustituimos DaoMock por el Dao
    @Provides
    @Singleton
    fun provideContactoRepository(
        contactoDao: ContactoDao
    ) : ContactoRepository = ContactoRepository(contactoDao)
}
