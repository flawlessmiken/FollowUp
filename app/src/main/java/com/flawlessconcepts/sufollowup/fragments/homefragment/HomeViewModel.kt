package com.flawlessconcepts.sufollowup.fragments.homefragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.flawlessconcepts.sufollowup.database.FollowUpDatabase
import com.flawlessconcepts.sufollowup.database.FollowUpDatabaseDao
import com.flawlessconcepts.sufollowup.database.FollowUpItem
import com.flawlessconcepts.sufollowup.utils.createLessonsFromJson
import com.flawlessconcepts.sufollowup.utils.formatlessons
import kotlinx.coroutines.launch


class HomeViewModel(
    val database: FollowUpDatabaseDao,
    application: Application
) : AndroidViewModel(application) {


    private var _singlelesson = MutableLiveData<FollowUpItem?>()
    val singlelesson: MutableLiveData<FollowUpItem?>
        get() = _singlelesson

    private fun initializeSingleLesson() {
        viewModelScope.launch {
            _singlelesson.value = getLessonFromDatabase()
        }
    }

    private val _mLessons = MediatorLiveData<List<FollowUpItem>>()

    val mLessons : LiveData<List<FollowUpItem>> = _mLessons  // 1

    fun allLessons() = viewModelScope.launch {  // 2
        _mLessons.postValue(database.getAllFollowUpLessons())   // 3
    }


//    private var _alllessons = MutableLiveData<List<FollowUpItem>>()
//    val alllessons: LiveData<List<FollowUpItem>>
//        get() = _alllessons
//
//    private fun initializeAllLesson() {
//        viewModelScope.launch {
//            _alllessons.value = getAllLessonFromDatabase()
//        }
//    }

    private suspend fun getAllLessonFromDatabase(): List<FollowUpItem> {
        var lessons = database.getAllFollowUpLessons()
        return lessons
    }
    private suspend fun getLessonFromDatabase(): FollowUpItem? {
        var lesson = database.getLastLesson()
        return lesson
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }


    init {
        populateDatabase()
        initializeSingleLesson()
        allLessons()
       // initializeAllLesson()

    }

    //private val lessons = database.getAllFollowUpLessons()

    val lessonString = Transformations.map(_mLessons) { lessons ->
      formatlessons(lessons, application.resources)
   }

    fun populateDatabase() {
        viewModelScope.launch {

            if (isDatabaseEmpty() == true){
                val lessons = createLessonsFromJson(getApplication())
                insert(lessons)

                _showSnackbarEvent.value = true
            }

        }
    }
    private suspend fun insert(items: List<FollowUpItem>) {
        database.addLessons(items)
    }

    private suspend fun isDatabaseEmpty(): Boolean? {
        val anyLesson = database.getAnyLesson()
        return anyLesson == null
    }




}



