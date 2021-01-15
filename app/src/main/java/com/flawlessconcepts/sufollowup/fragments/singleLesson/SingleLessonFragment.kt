package com.flawlessconcepts.sufollowup.fragments.singleLesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.flawlessconcepts.sufollowup.R
import com.flawlessconcepts.sufollowup.databinding.FragmentLessonListBinding
import com.flawlessconcepts.sufollowup.databinding.FragmentSingleLessonBinding


class SingleLessonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSingleLessonBinding>(
            inflater,
            R.layout.fragment_single_lesson, container, false)

        return binding.root
    }


}