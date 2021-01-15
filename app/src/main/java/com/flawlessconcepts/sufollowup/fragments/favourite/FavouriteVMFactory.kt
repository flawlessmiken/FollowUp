package com.flawlessconcepts.sufollowup.fragments.favourite

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flawlessconcepts.sufollowup.database.FollowUpDatabaseDao
import com.flawlessconcepts.sufollowup.fragments.homefragment.HomeViewModel

class FavouriteVMFactory(
    private val dataSource: FollowUpDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}