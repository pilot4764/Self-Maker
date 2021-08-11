package com.artsam.selfmaker.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Immutable model class for a User.
 * In order to compile with Room, we can't use @JvmOverloads to generate multiple constructors.
 *
 * @param id id of the user
 * @param title title of the user
 * @param description description of the user
 * @param isCompleted whether or not this task is completed
 */
@Entity(tableName = "habits")
data class Habit @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "description") var description: String = "",
    @ColumnInfo(name = "completed") var isCompleted: Boolean = false
) {
}