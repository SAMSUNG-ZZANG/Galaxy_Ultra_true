package com.example.sopt_main.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sopt_main.R
import com.example.sopt_main.base.BaseActivity
import com.example.sopt_main.databinding.ActivitySettingBinding
import com.example.sopt_main.util.SOPTSharedPreferences
import com.example.sopt_main.util.showToast

class SettingActivity : BaseActivity<ActivitySettingBinding>(R.layout.activity_setting) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
}