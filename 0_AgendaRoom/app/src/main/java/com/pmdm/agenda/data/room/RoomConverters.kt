package com.pmdm.agenda.data.room

import android.util.Base64
import androidx.room.TypeConverter

class RoomConverters {
    @TypeConverter
    fun toBlob(value: ByteArray?): String? =  Base64.encodeToString(value, Base64.DEFAULT)

    @TypeConverter
    fun fromBlob(value: String?): ByteArray? = Base64.decode(value, Base64.DEFAULT)
}
