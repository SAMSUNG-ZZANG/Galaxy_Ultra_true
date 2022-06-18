package com.example.sopt_main.server

import com.example.sopt_main.server.request.RequestSignIn
import com.example.sopt_main.server.request.RequestSignUp
import com.example.sopt_main.server.response.ResponseSignIn
import com.example.sopt_main.server.response.ResponseSignUp
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