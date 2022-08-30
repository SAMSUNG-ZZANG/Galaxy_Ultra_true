package com.example.sopt_main.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.bumptech.glide.Glide
import com.example.sopt_main.R
import com.example.sopt_main.adapter.TestViewPagerAdaptor
import com.example.sopt_main.base.BaseFragment
import com.example.sopt_main.databinding.FragmentProfileBinding
import com.example.sopt_main.ui.activity.SettingActivity


class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeFollowerBtn.isSelected =true
        binding.homeRepoBtn.isSelected =false
        transactionFragment()
        initImage()
        settingClickEvent()
    }


    private fun transactionFragment(){

        val fragment1 = FollowerFragment()
        val fragment2 = RepositoryFragment()

//        childFragmentManager.beginTransaction().add(R.id.profile_fragment,fragment1).commit()

        childFragmentManager.commit{
            replace<FollowerFragment>(R.id.profile_fragment)
        } //Fragment KTX를 사용하여 람다로 프래그먼트 트랜잭션을 단순화 할 수 있음

        binding.homeFollowerBtn.setOnClickListener{
            val transaction = childFragmentManager.beginTransaction()
            binding.homeFollowerBtn.isSelected =true
            binding.homeRepoBtn.isSelected =false
            transaction.replace(R.id.profile_fragment,fragment1).commit()
        }

        binding.homeRepoBtn.setOnClickListener{
            val transaction = childFragmentManager.beginTransaction()
            binding.homeRepoBtn.isSelected =true
            binding.homeFollowerBtn.isSelected =false
            transaction.replace(R.id.profile_fragment,fragment2)
            transaction.commit()
        }
    }

    private  fun initImage(){
        Glide.with(this)
            .load(R.drawable.selfie_true)
            .circleCrop()
            .into(binding.imageView)

    }

    private fun settingClickEvent(){
        binding.btnProfileSetting.setOnClickListener{
                val intent = Intent(activity, SettingActivity::class.java)
                startActivity(intent)
        }
    }


}