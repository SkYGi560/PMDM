package com.pmdm.agenda

import android.app.Application
import com.pmdm.agenda.data.ContactoRepository
import com.pmdm.agenda.data.mocks.contacto.ContactoDaoMock
import com.pmdm.agenda.data.room.contacto.ContactoDao
import com.pmdm.agenda.data.toContacto
import com.pmdm.agenda.data.toContactoEntity
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class AgendaApplication : Application(){
    @Inject
    lateinit var daoMock: ContactoDaoMock
    @Inject
    lateinit var repository: ContactoRepository
    override fun onCreate() {
        super.onCreate()
        runBlocking {
            if (repository.count() == 0)
                daoMock.get().forEach { repository.insert(it.toContacto())
                }
        }
    }
}