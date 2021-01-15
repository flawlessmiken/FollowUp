package com.flawlessconcepts.sufollowup.fragments.lessonlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.flawlessconcepts.sufollowup.R
import com.flawlessconcepts.sufollowup.database.FollowUpDatabase
import com.flawlessconcepts.sufollowup.databinding.FragmentHomeBinding
import com.flawlessconcepts.sufollowup.databinding.FragmentLessonListBinding
import com.flawlessconcepts.sufollowup.fragments.homefragment.HomeFragmentVMFactory
import com.flawlessconcepts.sufollowup.fragments.homefragment.HomeViewModel


class LessonListFragment : Fragment() {

    private lateinit var viewModel: LessonsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLessonListBinding>(
            inflater,
            R.layout.fragment_lesson_list, container, false)
        setUpDatabase()

        return binding.root
    }

    private fun setUpDatabase() {
        val application = requireNotNull(this.activity).application
        val dataSource = FollowUpDatabase.getInstance(application).followUpDatabaseDao
        val viewModelFactory = LessonVMFactory(dataSource, application)

        viewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(LessonsFragmentViewModel::class.java)
    }

}