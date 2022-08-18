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
import com.example.sopt_main.base.BaseFragment
import com.example.sopt_main.databinding.FragmentFollowerBinding
import com.example.sopt_main.databinding.FragmentSample3Binding
import com.example.sopt_main.ui.activity.HomeActivity
import com.example.sopt_main.ui.activity.SettingActivity
import com.example.sopt_main.ui.activity.SignInActivity
import com.example.sopt_main.util.SOPTSharedPreferences
import com.example.sopt_main.util.showToast

class SampleFragment3 : BaseFragment<FragmentSample3Binding>(R.layout.fragment_sample3) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initImage()
        btnClickEvent()
    }

    private  fun initImage(){
        Glide.with(this)
            .load(R.drawable.selfie_true)
            .circleCrop()
            .into(binding.ivProfile)
    }

    private fun btnClickEvent(){
        binding.btnNext.setOnClickListener{
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