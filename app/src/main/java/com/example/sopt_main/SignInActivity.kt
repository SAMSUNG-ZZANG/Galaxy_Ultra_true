package com.example.sopt_main

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.sopt_main.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK){
                val id = result.data?.getStringExtra("id")
                val password = result.data?.getStringExtra("pwd")
                binding.mainEditId.setText(id)
                binding.mainEditPwd.setText(password)
            }
        }

        binding.mainSignUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }

        initEvent()

    }

    private fun initEvent(){
        binding.mainLoginBtn.setOnClickListener{
            loginNetwork()
        }
    }

    private fun loginNetwork(){
        val requestSignIn = RequestSignIn(
            email = binding.mainEditId.text.toString(),
            password = binding.mainEditPwd.text.toString()
        )

//        Log.d(TAG, "loginNetwork: ${binding.mainEditId.text}, ${binding.mainEditPwd}")

        val call: Call<ResponseSignIn> = ServiceCreator.soptService.postLogin(requestSignIn)

        call.enqueue(object : Callback<ResponseSignIn> {
            override fun onResponse(
                call: Call<ResponseSignIn>,
                response: Response<ResponseSignIn>
            ){
                if(response.isSuccessful) {
                    val data = response.body()?.data


                    Toast.makeText(this@SignInActivity,"${data?.email}님 반갑습니다!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignInActivity,HomeActivity::class.java))
                }else{
                    Toast.makeText(this@SignInActivity,"로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@SignInActivity,"onresponse else", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }

        })

    }






}