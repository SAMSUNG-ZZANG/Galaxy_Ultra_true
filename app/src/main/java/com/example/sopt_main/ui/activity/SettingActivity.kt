package com.example.sopt_main.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sopt_main.databinding.ActivitySettingBinding
import com.example.sopt_main.util.SOPTSharedPreferences

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isAutoLogout()
    }

    private fun isAutoLogout() {
        binding.btnSettingLogout.setOnClickListener {
            SOPTSharedPreferences.setLogout(this)
            showToast("자동 로그인 해제")
            startActivity(Intent(this@SettingActivity, SignInActivity::class.java))
            finish()
        }
    }


    fun Context.showToast(msg:String){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }
}