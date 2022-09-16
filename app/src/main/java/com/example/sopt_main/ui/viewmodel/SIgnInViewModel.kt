package com.example.sopt_main.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sopt_main.enqueueUtil
import com.example.sopt_main.server.SoptService
import com.example.sopt_main.server.request.RequestSignIn
import com.example.sopt_main.server.response.ResponseSignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val soptService: SoptService
):ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _successLogin = MutableLiveData<Boolean?>()
    val successLogin = _successLogin

    fun signIn() {
        val requestSignIn = RequestSignIn(
            email = email.value ?: "",
            password = password.value ?: ""
        )

        val call: Call<ResponseSignIn> = soptService.postLogin(requestSignIn)

        call.enqueueUtil(
            onSuccess = {
                _successLogin.value = true
            },
            onError = {
                _successLogin.value = false
            }
        )
    }
}