package com.artsam.selfmaker.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.artsam.selfmaker.data.entities.Habit

/**
 * Data Access Object for the habits table.
 */
@Dao
interface HabitsDao {

    /**
     * Observes list of habits.
     *
     * @return all habits.
     */
    @Query("SELECT * FROM Habits")
    fun observeHabits(): LiveData<List<Habit>>

    /**
     * Observes a single habit.
     *
     * @param habitId the habit id.
     * @return the habit with habitId.
     */
    @Query("SELECT * FROM Habits WHERE id = :habitId")
    fun observeHabitById(habitId: String): LiveData<Habit>

    /**
     * Select all habits from the habits table.
     *
     * @return all habits.
     */
    @Query("SELECT * FROM Habits")
    suspend fun getHabits(): List<Habit>

    /**
     * Select a habit by id.
     *
     * @param habitId the habit id.
     * @return the habit with habitId.
     */
    @Query("SELECT * FROM Habits WHERE id = :habitId")
    suspend fun getHabitById(habitId: String): Habit?

    /**
     * Insert a habit in the database. If the habit already exists, replace it.
     *
     * @param habit the habit to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)

    /**
     * Update a habit.
     *
     * @param habit habit to be updated
     * @return the number of habits updated. This should always be 1.
     */
    @Update
    suspend fun updateHabit(habit: Habit): Int

    /**
     * Update the complete status of a habit
     *
     * @param habitId id of the habit
     * @param completed status to be updated
     */
    @Query("UPDATE Habits SET completed = :completed WHERE id = :habitId")
    suspend fun updateCompleted(habitId: String, completed: Boolean)

    /**
     * Delete a habit by id.
     *
     * @return the number of tasks deleted. This should always be 1.
     */
    @Query("DELETE FROM Habits WHERE id = :habitId")
    suspend fun deleteHabitById(habitId: String): Int

    /**
     * Delete all habits.
     */
    @Query("DELETE FROM Habits")
    suspend fun deleteHabits()

    /**
     * Delete all completed habits from the table.
     *
     * @return the number of habits deleted.
     */
    @Query("DELETE FROM Habits WHERE completed = 1")
    suspend fun deleteCompletedHabits(): Int
}