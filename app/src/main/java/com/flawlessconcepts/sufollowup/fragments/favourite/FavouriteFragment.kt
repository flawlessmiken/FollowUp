package com.flawlessconcepts.sufollowup.fragments.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.flawlessconcepts.sufollowup.R
import com.flawlessconcepts.sufollowup.database.FollowUpDatabase
import com.flawlessconcepts.sufollowup.databinding.FragmentFavouriteBinding
import com.flawlessconcepts.sufollowup.databinding.FragmentHomeBinding
import com.flawlessconcepts.sufollowup.fragments.homefragment.HomeFragmentVMFactory
import com.flawlessconcepts.sufollowup.fragments.homefragment.HomeViewModel


class FavouriteFragment : Fragment() {

    private lateinit var viewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFavouriteBinding>(
            inflater,
            R.layout.fragment_favourite, container, false)


        setUpDataBase(binding)
        return binding.root
    }

    fun setUpDataBase(binding: FragmentFavouriteBinding) {
        val application = requireNotNull(this.activity).application
        val dataSource = FollowUpDatabase.getInstance(application).followUpDatabaseDao
        val viewModelFactory = FavouriteVMFactory(dataSource, application)

        viewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(FavouriteViewModel::class.java)
    }



}