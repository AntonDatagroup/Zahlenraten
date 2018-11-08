package de.datagroup.zahlenraten.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Interface which provide methods for accessing Database Access Objects
 */

@Database(entities = [(Stat::class)], version = 1)

abstract class ZahlenratenDatabase : RoomDatabase() {

    /**
     * Returns the Database Access Object to deal with stats.
     * @return the Database Access Object to deal with stats
     */
    abstract fun statDao(): StatDao
}