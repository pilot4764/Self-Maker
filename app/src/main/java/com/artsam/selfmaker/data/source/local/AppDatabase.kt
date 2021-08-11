package com.artsam.selfmaker.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artsam.selfmaker.data.entities.Habit

/**
 * The Room Database that contains the Habit table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [Habit::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitsDao
}