package com.example.sopt_main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sopt_main.databinding.ActivityMainBinding
import com.example.sopt_main.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SignUpSet.setOnClickListener {

            if(binding.nameText.text.isNullOrBlank() || binding.SignUpIdText.text.isNullOrBlank() || binding.signUpPwdText.text.isNullOrBlank())  // 셋중 하나라도 비어있다면
            {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id",binding.SignUpIdText.text.toString())
                intent.putExtra("pwd",binding.signUpPwdText.text.toString())
                setResult(RESULT_OK, intent)

                finish() // 액티비티 종료 , SignINActivity 로 이동
            }

        }
    }


}