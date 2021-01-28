/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flawlessconcepts.sufollowup.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*


/**
 * Defines methods for using the SleepNight class with Room.
 */
@Dao
interface FollowUpDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FollowUpItem)

    @Update
    suspend fun update(followUpItem: FollowUpItem)

    @Query("SELECT * from followup_item_table WHERE followUpID = :key")
    suspend fun getFollowByID(key: Int): FollowUpItem?

    @Query("SELECT * FROM followup_item_table ORDER BY followUpID DESC LIMIT 1")
    suspend fun getLastLesson(): FollowUpItem?

    @Query("SELECT * from followup_item_table ORDER BY followUpID DESC")
     suspend  fun getAllFollowUpLessons(): List<FollowUpItem>

//    @Query("SELECT * FROM followup_item_table WHERE favourite ORDER BY followUpID DESC LIMIT 4")
//       fun getFavourites(): LiveData<List<FollowUpItem>>


    @Query("SELECT * FROM followup_item_table")
    suspend fun getFavourites(): List<FollowUpItem>


    @Query("SELECT * FROM  followup_item_table ORDER BY followUpID DESC LIMIT 4")
     fun getSomeLessons(): LiveData<List<FollowUpItem>>


    @Query("SELECT * FROM followup_item_table LIMIT 1")
    suspend  fun getAnyLesson(): FollowUpItem?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLessons(lessons: List<FollowUpItem>?): LongArray?


//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertLessons(vararg lessons: List<FollowUpItem>?)

//    @Query("DELETE FROM daily_sleep_quality_table")
//    fun clear()
//
//    /**
//     * Selects and returns all rows in the table,
//     *
//     * sorted by start time in descending order.
//     */
//    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
//    fun getAllNights(): LiveData<List<FollowUpItem>>
//
//    /**
//     * Selects and returns the latest night.
//     */
//   @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
//    fun getTonight(): FollowUpItem?
}
