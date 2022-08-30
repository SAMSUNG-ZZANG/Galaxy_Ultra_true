package com.example.sopt_main.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopt_main.R
import com.example.sopt_main.adapter.TabViewPagerAdaptor
import com.example.sopt_main.base.BaseFragment
import com.example.sopt_main.databinding.FragmentHomeBinding
import com.example.sopt_main.ui.activity.SettingActivity
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var tabViewPagerAdaptor: TabViewPagerAdaptor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdaptor()
        initTabLayout()
    }

    private fun initAdaptor() {
        val fragmentList = listOf(TabFollowerFragment(), TabFollowingFragment())

        tabViewPagerAdaptor = TabViewPagerAdaptor(this)
        tabViewPagerAdaptor.fragments.addAll(fragmentList)

        binding.homeVp.adapter = tabViewPagerAdaptor
    }

    private fun initTabLayout() {

        val tabLabel = resources.getStringArray(R.array.follower_list)

        TabLayoutMediator(binding.homeTap, binding.homeVp) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }


}