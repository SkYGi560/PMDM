package com.pmdm.recetario.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pmdm.recetario.data.room.recetas.RecetaDao
import com.pmdm.recetario.data.room.recetas.RecetaEntity

@Database(
    entities = [RecetaEntity::class],
    version = 1
)
abstract class RecetasDB: RoomDatabase() {
    abstract fun recetaDao() : RecetaDao
    companion object {
        fun getDatabase(
            context: Context
        ) = Room.databaseBuilder(
            context,
            RecetasDB::class.java, "recetas.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}
