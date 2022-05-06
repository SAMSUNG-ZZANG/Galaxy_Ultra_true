package com.example.sopt_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.sopt_main.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var testViewPagerAdaptor: TestViewPagerAdaptor



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater,container,false)

        binding.homeFollowerBtn.isSelected =true
        binding.homeRepoBtn.isSelected =false
        transactionFragment()
        initImage()

        return binding.root
    }


    private fun transactionFragment(){

        val fragment1 =FollowerFragment()
        val fragment2 =RepositoryFragment()

        childFragmentManager.beginTransaction().add(R.id.profile_fragment,fragment1).commit()

        binding.homeFollowerBtn.setOnClickListener{
            val transaction = childFragmentManager.beginTransaction()
            binding.homeFollowerBtn.isSelected =true
            binding.homeRepoBtn.isSelected =false
            transaction.replace(R.id.profile_fragment,fragment1)
            transaction.commit()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}