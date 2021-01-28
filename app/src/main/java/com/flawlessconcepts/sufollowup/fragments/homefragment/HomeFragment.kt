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
            ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)


        showSnackback()
        showSnackbackforLessons()

        binding.homeViewModel = viewModel
        binding.setLifecycleOwner(this)
    }

    private fun showSnackback() {
        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.done_inserting_message),
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                // Reset state to make sure the snackbar is only shown once, even if the device
                // has a configuration change.
                viewModel.doneShowingSnackbar()
            }
        })
    }

    private fun showSnackbackforLessons() {
        viewModel.mLessons.observe(viewLifecycleOwner, Observer {
            if (it != null) { // Observed state is true.
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                   "yes data " + it.size,
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                // Reset state to make sure the snackbar is only shown once, even if the device
                // has a configuration change.

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