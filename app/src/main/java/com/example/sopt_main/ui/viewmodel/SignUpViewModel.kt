package com.example.sopt_main.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sopt_main.enqueueUtil
import com.example.sopt_main.server.ServiceCreator
import com.example.sopt_main.server.SoptService
import com.example.sopt_main.server.request.RequestSignUp
import com.example.sopt_main.server.response.ResponseSignUp
import com.example.sopt_main.util.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val soptService: SoptService)
    :ViewModel(){

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _signUP = MutableLiveData<Boolean>()
    val signUp = _signUP

    fun checkInput(): Boolean = name.value.isNullOrBlank()||email.value.isNullOrBlank() || password.value.isNullOrBlank()

    fun signUp(){
        val requestSignUp = RequestSignUp(
            name = name.value ?: "",
            email = email.value ?: "",
            password = password.value ?: ""
        )

        val call: Call<ResponseSignUp> = soptService.postSignUp(requestSignUp)

        call.enqueueUtil(
            onSuccess = {
                _signUP.value =true
            },
            onError = {
                _signUP.value =false
            }
        )
    }




}