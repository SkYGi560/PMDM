package com.pmdm.agenda.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pmdm.agenda.data.room.contacto.ContactoDao
import com.pmdm.agenda.data.room.contacto.ContactoEntity


@Database(
    entities = [ContactoEntity::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class AgendaDb : RoomDatabase() {
    abstract fun contactoDao(): ContactoDao

    companion object {
        fun getDatabase(context: Context) = Room.databaseBuilder(
            context,
            AgendaDb::class.java, "agenda"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}