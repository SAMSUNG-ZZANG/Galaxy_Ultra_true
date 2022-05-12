package com.example.sopt_main.Git_follower

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{username}/following")
    fun getUserInfo(@Path("username")username:String): Call<List<ResponseFollowerInfo>>
}