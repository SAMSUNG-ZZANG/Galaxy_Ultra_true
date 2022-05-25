package com.example.sopt_main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sopt_main.Server.RequestSignUp
import com.example.sopt_main.Server.ResponseSignUp
import com.example.sopt_main.Server.ServiceCreator
import com.example.sopt_main.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        call.enqueue(object : Callback<ResponseSignUp> {
            override fun onResponse(
                call: Call<ResponseSignUp>,
                response: Response<ResponseSignUp>
            ){

                if(response.isSuccessful) {
                    val data = response.body()?.data
                    Toast.makeText(this@SignUpActivity,"${data?.id}님 반갑습니다!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@SignUpActivity,"회원가입 실패", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@SignUpActivity,"onresponse else", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }

        })

    }


}