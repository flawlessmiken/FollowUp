package com.flawlessconcepts.sufollowup.fragments.homefragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.flawlessconcepts.sufollowup.R
import com.flawlessconcepts.sufollowup.database.FollowUpDatabase
import com.flawlessconcepts.sufollowup.databinding.FragmentHomeBinding
import com.flawlessconcepts.sufollowup.fragments.homefragment.HomeViewModel
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {


    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home, container, false)
        setHasOptionsMenu(true)

        setUpDataBase(binding)

        return binding.root
    }


    fun setUpDataBase(binding: FragmentHomeBinding) {
        val application = requireNotNull(this.activity).application
        val dataSource = FollowUpDatabase.getInstance(application).followUpDatabaseDao
        val viewModelFactory = HomeFragmentVMFactory(dataSource, application)

        viewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(HomeViewModel::class.java)


        showSnackback()

        binding.homeViewModel = viewModel
    }

    private fun showSnackback() {
        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
//                Snackbar.make(
//                   // apfindViewById(android.R.id.content),
//                    "", Snackbar.LENGTH_SHORT ).show()
//
//                viewModel.doneShowingSnackbar()
            }
        })
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


}