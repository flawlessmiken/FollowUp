package com.flawlessconcepts.sufollowup.fragments.singleLesson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flawlessconcepts.sufollowup.database.FollowUpDatabaseDao

class SingleLessonVMFactory (
    private val lessonKey: Long,
    private val dataSource: FollowUpDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleLessonViewModel::class.java)) {
            return SingleLessonViewModel(lessonKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}