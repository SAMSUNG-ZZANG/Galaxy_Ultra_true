package com.example.sopt_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopt_main.databinding.FragmentHomeBinding
import com.example.sopt_main.databinding.FragmentProfileBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var tabViewPagerAdaptor: TabViewPagerAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        initAdaptor()
        initTabLayout()

        return binding.root

    }

    private fun initAdaptor(){
        val fragmentList = listOf(TabFollowerFragment(),TabFollowingFragment())

        tabViewPagerAdaptor = TabViewPagerAdaptor(this)
        tabViewPagerAdaptor.fragments.addAll(fragmentList)

        binding.homeVp.adapter = tabViewPagerAdaptor
    }

    private fun initTabLayout(){

        val tabLabel = resources.getStringArray(R.array.follower_list) //

        TabLayoutMediator(binding.homeTap, binding.homeVp) {tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }

}