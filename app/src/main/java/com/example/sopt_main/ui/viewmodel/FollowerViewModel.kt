package com.example.sopt_main.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sopt_main.enqueueUtil
import com.example.sopt_main.server.GithubService
import com.example.sopt_main.server.response.ResponseFollowerInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FollowerViewModel @Inject constructor(
    private val githubService: GithubService
): ViewModel() {
    private val _followerList = MutableLiveData<List<ResponseFollowerInfo>>()
    val followerList: LiveData<List<ResponseFollowerInfo>> = _followerList

    fun fetchFollowers() {
        val call: Call<List<ResponseFollowerInfo>> = githubService.getUserInfo("jinsilWoo")
        call.enqueueUtil(
            onSuccess =  {
                _followerList.value = it
        }, onError = {

        })
    }
}