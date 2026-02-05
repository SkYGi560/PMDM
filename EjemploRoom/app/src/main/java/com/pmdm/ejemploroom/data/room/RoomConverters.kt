package com.pmdm.ejemploroom.data.room

import androidx.room.TypeConverter
import java.time.*;


// Estamos presuponiendo que nuestras fechas nunca son nulas.
class RoomConverters {
    @TypeConverter
    fun fromTimestamp(value: Int): LocalDate {
        return LocalDate.ofEpochDay(value.toLong())
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate): Int{
        return date.toEpochDay().toInt()
    }
}