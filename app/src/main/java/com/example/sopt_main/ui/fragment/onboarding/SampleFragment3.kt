package com.example.sopt_main.ui.fragment.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.example.sopt_main.R
import com.example.sopt_main.adapter.FollowerAdapter
import com.example.sopt_main.databinding.FragmentFollowerBinding
import com.example.sopt_main.databinding.FragmentSample3Binding
import com.example.sopt_main.ui.activity.HomeActivity
import com.example.sopt_main.ui.activity.SettingActivity
import com.example.sopt_main.ui.activity.SignInActivity
import com.example.sopt_main.util.SOPTSharedPreferences
import com.example.sopt_main.util.showToast

class SampleFragment3 : Fragment() {

    private var _binding : FragmentSample3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSample3Binding.inflate(layoutInflater,container,false)


        initImage()
        btnClickEvent()

        return binding.root
    }

    private  fun initImage(){
        Glide.with(this)
            .load(R.drawable.selfie_true)
            .circleCrop()
            .into(binding.ivProfile)
    }

    private fun btnClickEvent(){
        _binding?.btnNext?.setOnClickListener{
            isAutoLogin()
        }
    }

    private fun isAutoLogin() {
        if(SOPTSharedPreferences.getAutoLogin(requireContext())){
            requireContext().showToast("자동로그인 되었습니다")
            startActivity(Intent(requireActivity(), HomeActivity::class.java))
            requireActivity().finish()
        } else {
            startActivity(Intent(requireActivity(), SignInActivity::class.java))
            requireActivity().finish()
        }
    }


}