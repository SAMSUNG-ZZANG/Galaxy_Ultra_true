package com.example.sopt_main.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sopt_main.server.request.RequestSignIn
import com.example.sopt_main.server.response.ResponseSignIn
import com.example.sopt_main.server.ServiceCreator
import com.example.sopt_main.databinding.ActivityMainBinding
import com.example.sopt_main.enqueueUtil
import com.example.sopt_main.util.SOPTSharedPreferences
import retrofit2.Call

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
        initClickEvent()
        isAutoLogin()

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

        val call: Call<ResponseSignIn> = ServiceCreator.soptService.postLogin(requestSignIn)

        call.enqueueUtil(
            onSuccess = {
                    Toast.makeText(this@SignInActivity,"${it.data?.email}님 반갑습니다!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
            },
            onError = {
                Toast.makeText(this@SignInActivity,"로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@SignInActivity,"onresponse else", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun initClickEvent(){
        binding.ivSignInCheckbox.setOnClickListener{
            binding.ivSignInCheckbox.isSelected =!binding.ivSignInCheckbox.isSelected
            SOPTSharedPreferences.setAutoLogin(this,binding.ivSignInCheckbox.isSelected)
        }

    }

   private fun isAutoLogin() {
        if(SOPTSharedPreferences.getAutoLogin(this)){
            showToast("자동로그인 되었습니다")
            startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
            finish()
        }
    }


    fun Context.showToast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

}