package com.flawlessconcepts.sufollowup.fragments.lessonlist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flawlessconcepts.sufollowup.database.FollowUpDatabaseDao
import com.flawlessconcepts.sufollowup.fragments.homefragment.HomeViewModel

class LessonVMFactory(
    private val dataSource: FollowUpDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LessonsFragmentViewModel::class.java)) {
            return LessonsFragmentViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}