package com.example.sopt_main.util

import android.content.Context
import android.content.SharedPreferences

object SOPTSharedPreferences {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"

    fun init(context: Context):SharedPreferences{
        return context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
    }


    //파일 읽기
    fun getAutoLogin(context: Context):Boolean{
        return init(context).getBoolean(AUTO_LOGIN, false)
    }

    //파일 쓰기
    fun setAutoLogin(context: Context,value: Boolean){
        init(context).edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun setLogout(context: Context): Boolean{
        init(context).edit()
            .remove(AUTO_LOGIN) //key 값에 해당하는 value 삭제
            .clear() //모든 값을 지움
            .apply()

        return init(context).getBoolean(AUTO_LOGIN, false)
    }

}