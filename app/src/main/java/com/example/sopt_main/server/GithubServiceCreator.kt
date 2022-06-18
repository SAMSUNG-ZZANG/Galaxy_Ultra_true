package com.example.sopt_main.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubServiceCreator {
    private const val BASE_URL = "https://api.github.com/" //작대기...

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        val githubService: GithubService = retrofit.create(GithubService::class.java)

}