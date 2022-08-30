package com.example.sopt_main.ui.fragment.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sopt_main.R
import com.example.sopt_main.base.BaseFragment
import com.example.sopt_main.databinding.FragmentSample1Binding

class SampleFragment1 : BaseFragment<FragmentSample1Binding>(R.layout.fragment_sample1) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_sampleFragment1_to_sampleFragment2)
        }

    }
}