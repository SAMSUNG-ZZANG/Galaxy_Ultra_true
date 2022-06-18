package com.example.sopt_main.ui.fragment.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sopt_main.R
import com.example.sopt_main.databinding.FragmentSample2Binding


class SampleFragment2 : Fragment() {

    private var _binding : FragmentSample2Binding? = null
    private val binding get() = _binding ?: error("Bidnding 이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSample2Binding.inflate(layoutInflater,container,false)

        binding.btnNext.setOnClickListener{
            findNavController().navigate(R.id.action_sampleFragment2_to_sampleFragment3)
        }

        return binding.root
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }

}

