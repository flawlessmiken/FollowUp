package com.flawlessconcepts.sufollowup.fragments.singleLesson

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flawlessconcepts.sufollowup.database.FollowUpDatabaseDao
import com.flawlessconcepts.sufollowup.database.FollowUpItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SingleLessonViewModel (
    lessonKey: Int,
    val dataSource: FollowUpDatabaseDao) : ViewModel() {


    val database = dataSource
    val lessonId = lessonKey
    private val viewModelJob = Job()

    private var lesson = MutableLiveData<FollowUpItem?>()


    init {

    }

    private fun initializeLesson() {
        viewModelScope.launch {
            lesson.value = getLessonFromDatabase(lessonId)
        }
    }



    private suspend fun getLessonFromDatabase(lessonKey: Int): FollowUpItem? {
       var lesson = database.getFollowByID(lessonKey)
        return lesson
    }

    private suspend fun likeButtonClicked(followUpItem: FollowUpItem) {
        followUpItem.favourite = !followUpItem.favourite
        database.update(followUpItem)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("LessonViewModel", "LessonViewModel destroyed!")
    }

}