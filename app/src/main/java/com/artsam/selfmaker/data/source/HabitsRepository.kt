/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.artsam.selfmaker.data.source

import androidx.lifecycle.LiveData
import com.artsam.selfmaker.data.Result
import com.artsam.selfmaker.data.entities.Habit

/**
 * Interface to the data layer.
 */
interface HabitsRepository {

    fun observeHabits(): LiveData<Result<List<Habit>>>

    fun observeHabit(habitId: String): LiveData<Result<Habit>>

    suspend fun getHabits(forceUpdate: Boolean = false): Result<List<Habit>>

    suspend fun getHabit(habitId: String, forceUpdate: Boolean = false): Result<Habit>

    suspend fun refreshHabits()

    suspend fun refreshHabit(habitId: String)

    suspend fun saveHabit(habit: Habit)

    suspend fun completeHabit(habit: Habit)

    suspend fun completeHabit(habitId: String)

    suspend fun activateHabit(habit: Habit)

    suspend fun activateHabit(habitId: String)

    suspend fun clearCompletedHabits()

    suspend fun deleteAllHabits()

    suspend fun deleteHabit(habitId: String)
}
