package com.example.sopt_main.data

import com.example.sopt_main.data.service.GithubService
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