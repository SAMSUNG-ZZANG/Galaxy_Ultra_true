package com.example.sopt_main.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.sopt_main.R
import com.example.sopt_main.base.BaseActivity
import com.example.sopt_main.databinding.ActivityMainBinding
import com.example.sopt_main.ui.viewmodel.SignInViewModel
import com.example.sopt_main.util.SOPTSharedPreferences
import com.example.sopt_main.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK){
                val id = result.data?.getStringExtra("id") ?: ""
                val password = result.data?.getStringExtra("pwd") ?: ""
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
        loginNetwork()

    }

    private fun initEvent(){
        binding.mainLoginBtn.setOnClickListener{
           viewModel.signIn()
        }
    }

    private fun loginNetwork(){
        viewModel.successLogin.observe(this) { isSuccessLogin ->
            if (isSuccessLogin == true) {
                showToast("${viewModel.email.value}님 반갑습니다!")
                startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
            } else if (isSuccessLogin == false) {
                showToast("로그인에 실패하셨습니다.")
            }
        }
    }

    private fun initClickEvent(){
        binding.ivSignInCheckbox.setOnClickListener{
            binding.ivSignInCheckbox.isSelected =!binding.ivSignInCheckbox.isSelected
            SOPTSharedPreferences.setAutoLogin(this, binding.ivSignInCheckbox.isSelected)
        }
    }
}