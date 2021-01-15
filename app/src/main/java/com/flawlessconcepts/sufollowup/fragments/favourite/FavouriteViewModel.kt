package com.flawlessconcepts.sufollowup.fragments.favourite

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.flawlessconcepts.sufollowup.database.FollowUpDatabase
import com.flawlessconcepts.sufollowup.database.FollowUpDatabaseDao



class FavouriteViewModel (
    val database: FollowUpDatabaseDao,
    application: Application) : AndroidViewModel(application)
    {

        init {
            Log.i("HomeViewModel", "HomeViewModel created!")
        }

        override fun onCleared() {
            super.onCleared()
            Log.i("HomeViewModel", "HomeViewModel destroyed!")
        }
    }