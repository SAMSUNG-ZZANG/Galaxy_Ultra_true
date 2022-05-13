package com.example.sopt_main.Server

data class ResponseSignUp(
    val status: Int,
    val message: String,
    val data: Data
) {
    data class Data(
        val id : Int
    )
}