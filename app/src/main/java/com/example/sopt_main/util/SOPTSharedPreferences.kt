package com.example.sopt_main.util

import android.content.Context
import android.content.SharedPreferences

object SOPTSharedPreferences {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private lateinit var preferences: SharedPreferences

    fun init(context: Context){
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
    }


    //파일 읽기
    fun getAutoLogin():Boolean{
        return preferences.getBoolean(AUTO_LOGIN, false)
    }

    //파일 쓰기
    fun setAutoLogin(value: Boolean){
        preferences.edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun setLogout(context: Context){
        preferences = context.getSharedPreferences(STORAGE_KEY,Context.MODE_PRIVATE)
        preferences.edit()
            .remove(AUTO_LOGIN) //key 값에 해당하는 value 삭제
            .clear() //모든 값을 지움
            .apply()
    }

}