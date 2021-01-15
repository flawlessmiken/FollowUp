package com.flawlessconcepts.sufollowup.fragments.lessonlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.flawlessconcepts.sufollowup.database.FollowUpDatabaseDao

class LessonsFragmentViewModel(
    val database: FollowUpDatabaseDao,
    application: Application) : AndroidViewModel(application) {


    init {
        Log.i("LessonViewModel", "LessonViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("LessonViewModel", "LessonViewModel destroyed!")
    }
}