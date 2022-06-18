package com.example.sopt_main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TestViewPagerAdaptor(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity){
        val fragments = mutableListOf<Fragment>()

        override fun getItemCount() : Int = fragments.size

        override fun createFragment(position: Int) : Fragment = fragments[position]
    }
