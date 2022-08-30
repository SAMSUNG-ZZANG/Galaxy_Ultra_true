package com.example.sopt_main.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.sopt_main.R
import com.example.sopt_main.base.BaseActivity
import com.example.sopt_main.server.request.RequestSignUp
import com.example.sopt_main.server.response.ResponseSignUp
import com.example.sopt_main.server.ServiceCreator
import com.example.sopt_main.databinding.ActivitySignUpBinding
import com.example.sopt_main.enqueueUtil
import com.example.sopt_main.ui.viewmodel.SignUpViewModel
import com.example.sopt_main.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.signUpViewModel = viewModel

        binding.signUpSetBtn.setOnClickListener {
            if (viewModel.checkInput()) {
                showToast("입력되지 않은 정보가 있습니다")
            } else {
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id",binding.signUpEditId.text.toString())
                intent.putExtra("pwd",binding.signUpEditPwd.text.toString())
                setResult(RESULT_OK, intent)
                viewModel.signUp()
                signUpNetwork()
                finish() // 액티비티 종료 , SignINActivity 로 이동
            }
        }
    }

//activity 일때는 this , fragment 일때는 lifecycleowner 왜냐 생명주기때문에
    private fun signUpNetwork(){
        viewModel.signUp.observe(this){ isSignUp->
            if(isSignUp == true){
                 showToast("${viewModel.email.value}님 반갑습니다!")
            } else if (isSignUp == false) {
                showToast("회원가입에 실패하셨습니다.")
            }
        }
    }
}