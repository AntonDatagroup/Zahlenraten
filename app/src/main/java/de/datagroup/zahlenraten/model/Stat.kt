package de.datagroup.zahlenraten.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import java.util.Date

@TypeConverters(Converters::class)
@Entity
data class Stat(
    val deviceId: String,
    @PrimaryKey
    val id: Int,
    val gameStartTime: Date,
    val randomNumber: Int,
    val guessesCount: Int,
    val hasWon: Boolean
)

class Converters {
@TypeConverter
fun fromTimestamp(value: Long?): Date? {
return value?.let { Date(it) }
}

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
    return date?.time?.toLong()
    }
}