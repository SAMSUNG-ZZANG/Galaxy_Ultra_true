package com.example.sopt_main.ui.activity

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.sopt_main.ui.fragment.HomeFragment
import com.example.sopt_main.R
import com.example.sopt_main.ui.fragment.TestFragment3
import com.example.sopt_main.adapter.TestViewPagerAdaptor
import com.example.sopt_main.base.BaseActivity
import com.example.sopt_main.databinding.ActivityHomeBinding
import com.example.sopt_main.ui.fragment.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private lateinit var testViewPagerAdaptor: TestViewPagerAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdaptor()
        initBottomNavi()

    }
    private fun initAdaptor(){
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), TestFragment3())
        testViewPagerAdaptor = TestViewPagerAdaptor(this)
        testViewPagerAdaptor.fragments.addAll(fragmentList)

        binding.homeVp.adapter = testViewPagerAdaptor
    }

    private fun initBottomNavi(){
        binding.homeVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.homeBnv.menu.getItem(position).isChecked = true
            }
        })

        binding.homeBnv.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_android -> {
                    binding.homeVp.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_list -> {
                    binding.homeVp.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.homeVp.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }

        }
    }

    companion object{
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}