package com.flawlessconcepts.sufollowup.fragments.homefragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.flawlessconcepts.sufollowup.database.FollowUpDatabase
import com.flawlessconcepts.sufollowup.database.FollowUpDatabaseDao
import com.flawlessconcepts.sufollowup.database.FollowUpItem
import com.flawlessconcepts.sufollowup.utils.formatlessons
import kotlinx.coroutines.launch


class HomeViewModel(
    val database: FollowUpDatabaseDao,
    application: Application) : AndroidViewModel(application) {


    private var favourites =   MutableLiveData<List<FollowUpItem>>()
     val someLessons =   MutableLiveData<List<FollowUpItem>>()


    init {
        initializeFavourites()
        initializeLessons()
    }



    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }


    private fun initializeFavourites() {
        viewModelScope.launch {
            someLessons.value = getLessonsFromDatabase()
        }
    }
    private fun initializeLessons() {
        viewModelScope.launch {
            favourites.value = getLFavouriteFromDatabase()
        }
    }



    private suspend fun getLessonsFromDatabase(): List<FollowUpItem>? {
        val somelessons = database.getSomeLessons()
        return somelessons.value
    }

    private suspend fun getLFavouriteFromDatabase(): List<FollowUpItem>? {
        val favourites = database.getFavourites()
        return favourites.value
    }
    val somelessonString = Transformations.map(someLessons) { somelessons ->
        //formatlessons(somelessons, application.resources)
        "title"+somelessons.get(1).title +somelessons.get(2).title
    }

    val somelessonStrings ="mmmmmmmmmmmm"



    override fun onCleared() {
        super.onCleared()
        Log.i("HomeViewModel", "HomeViewModel destroyed!")
    }
}



