package com.example.sopt_main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sopt_main.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var home_binding : ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        home_binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(home_binding.root)

        transactionFragment()
          }

    private fun transactionFragment(){

        val fragment1 =FollowerFragment()
        val fragment2 =RepositoryFragment()

        supportFragmentManager.beginTransaction().add(R.id.home_fragment,fragment1).commit()

        home_binding.homeFollowerBtn.setOnClickListener{
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.home_fragment,fragment1)
            transaction.commit()
        }

        home_binding.homeRepoBtn.setOnClickListener{
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.home_fragment,fragment2)
            transaction.commit()
        }
    }


}