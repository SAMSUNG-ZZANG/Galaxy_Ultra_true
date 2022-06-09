package com.example.sopt_main.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sopt_main.server.request.RequestSignUp
import com.example.sopt_main.server.response.ResponseSignUp
import com.example.sopt_main.server.ServiceCreator
import com.example.sopt_main.databinding.ActivitySignUpBinding
import com.example.sopt_main.enqueueUtil
import retrofit2.Call


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpSetBtn.setOnClickListener {

            if(binding.signUpEditName.text.isNullOrBlank() || binding.signUpEditId.text.isNullOrBlank() || binding.signUpEditPwd.text.isNullOrBlank())  // 셋중 하나라도 비어있다면
            {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id",binding.signUpEditId.text.toString())
                intent.putExtra("pwd",binding.signUpEditPwd.text.toString())
                setResult(RESULT_OK, intent)

                signUpNetwork()
                finish() // 액티비티 종료 , SignINActivity 로 이동
            }

        }
    }


    private fun signUpNetwork(){
        val requestSignUp = RequestSignUp(
            name = binding.signUpEditName.text.toString(),
            email = binding.signUpEditId.text.toString(),
            password = binding.signUpEditPwd.text.toString()
        )

        val call: Call<ResponseSignUp> = ServiceCreator.soptService.postSignUp(requestSignUp)

        call.enqueueUtil(
            onSuccess = {
                    Toast.makeText(this@SignUpActivity,"${it.data?.id}님 반갑습니다!", Toast.LENGTH_SHORT).show()
            },
            onError = {
                Toast.makeText(this@SignUpActivity,"회원가입 실패", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@SignUpActivity,"onresponse else", Toast.LENGTH_SHORT).show()
            }
        )
    }
}