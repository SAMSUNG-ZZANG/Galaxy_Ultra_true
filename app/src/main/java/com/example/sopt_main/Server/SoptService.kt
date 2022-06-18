package com.example.sopt_main.Server

import com.example.sopt_main.Server.request.RequestSignIn
import com.example.sopt_main.Server.request.RequestSignUp
import com.example.sopt_main.Server.response.ResponseSignIn
import com.example.sopt_main.Server.response.ResponseSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signin")
    fun postLogin(
        @Body body: RequestSignIn
    ): Call<ResponseSignIn>


    @POST("auth/signup")
    fun postSignUp(
        @Body body: RequestSignUp
    ): Call<ResponseSignUp>
}