package com.example.sopt_main.response

data class ResponseSignIn(
    val status: Int,
    val message: String,
    val data: Data
){
    data class Data(
        val name: String,
        val email: String
    )
}
