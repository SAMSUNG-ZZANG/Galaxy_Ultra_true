package com.example.sopt_main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sopt_main.databinding.ActivityMainBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateinit var resultLauncher: ActivityResultLauncher<Intent>
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK){
                val id = result.data?.getStringExtra("id")
                val password = result.data?.getStringExtra("pwd")
                binding.idText.setText(id)
                binding.pwdText.setText(password)
            }
        }
        binding.login.setOnClickListener {
            //Toast.makeText(this,"안녕하세요. 진실님",Toast.LENGTH_SHORT).show()

            if(binding.idText.text.isNullOrBlank() || binding.pwdText.text.isNullOrBlank())  //둘 중 하나라도 비어있다면
            {
                Toast.makeText(this,"아이디/비밀번호를 확인해주세요",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()
            }
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }


    }



}