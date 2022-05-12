package com.example.sopt_main.Git_follower

import retrofit2.Retrofit

object GithubServiceCreator {
    private const val BASE_URL = "https://api.github.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .build()

        val githubService: GithubService = retrofit.create(GithubService::class.java)

}