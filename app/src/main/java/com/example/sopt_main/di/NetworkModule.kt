package com.example.sopt_main.di

import com.example.sopt_main.server.GithubService
import com.example.sopt_main.server.SoptService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SoptRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class GithubRetrofit

    private const val GITHUB_URL = "https://api.github.com/"
    private const val SOPT_URL = "http://13.124.62.236/"

    @Provides
    @Singleton
    @GithubRetrofit
    fun provideGithubRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    @SoptRetrofit
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(SOPT_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideGithubService(@GithubRetrofit retrofit: Retrofit): GithubService = retrofit.create(GithubService::class.java)

    @Provides
    @Singleton
    fun provideSoptService(@SoptRetrofit retrofit: Retrofit): SoptService = retrofit.create(SoptService::class.java)
}