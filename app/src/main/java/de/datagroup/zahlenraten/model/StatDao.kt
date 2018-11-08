package de.datagroup.zahlenraten.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * The database object in charge of inserting Stat objects and retrieving them from Database.
 * @property all the list of all Stats in database
 */

@Dao
interface StatDao {
    @get:Query("SELECT * FROM stat")
    val all: List<Stat>
    /**
     * Inserts specified statss in database.
     * @param stats all the stats to insert in database
     */

    @Insert
    fun insertAll(vararg stats: Stat)
}