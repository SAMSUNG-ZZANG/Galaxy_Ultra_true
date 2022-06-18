package com.example.sopt_main.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.sopt_main.ui.fragment.HomeFragment
import com.example.sopt_main.R
import com.example.sopt_main.TestFragment3
import com.example.sopt_main.adapter.TestViewPagerAdaptor
import com.example.sopt_main.databinding.ActivityHomeBinding
import com.example.sopt_main.ui.fragment.ProfileFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding : ActivityHomeBinding
    private lateinit var testViewPagerAdaptor: TestViewPagerAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        initAdaptor()
        initBottomNavi()

    }
    private fun initAdaptor(){
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), TestFragment3())
        testViewPagerAdaptor = TestViewPagerAdaptor(this)
        testViewPagerAdaptor.fragments.addAll(fragmentList)

        homeBinding.homeVp.adapter = testViewPagerAdaptor
    }

    private fun initBottomNavi(){
        homeBinding.homeVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                homeBinding.homeBnv.menu.getItem(position).isChecked = true
            }
        })

        homeBinding.homeBnv.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_android -> {
                    homeBinding.homeVp.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_list -> {
                    homeBinding.homeVp.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    homeBinding.homeVp.currentItem = THIRD_FRAGMENT
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