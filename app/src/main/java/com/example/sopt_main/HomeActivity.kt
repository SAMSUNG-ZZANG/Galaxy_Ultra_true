package com.example.sopt_main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView
import androidx.viewpager2.widget.ViewPager2
import com.example.sopt_main.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var home_binding : ActivityHomeBinding
    private lateinit var testViewPagerAdaptor: TestViewPagerAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        home_binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(home_binding.root)

        initAdaptor()
        initBottomNavi()

    }
    private fun initAdaptor(){
        val fragmentList = listOf(ProfileFragment(),HomeFragment(),TestFragment3())
        testViewPagerAdaptor = TestViewPagerAdaptor(this)
        testViewPagerAdaptor.fragments.addAll(fragmentList)

        home_binding.homeVp.adapter = testViewPagerAdaptor
    }

    private fun initBottomNavi(){
        home_binding.homeVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                home_binding.homeBnv.menu.getItem(position).isChecked = true
            }
        })

        home_binding.homeBnv.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_android -> {
                    home_binding.homeVp.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_list -> {
                    home_binding.homeVp.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    home_binding.homeVp.currentItem = THIRD_FRAGMENT
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