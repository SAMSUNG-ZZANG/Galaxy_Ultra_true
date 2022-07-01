package com.example.sopt_main.server.response
import kotlinx.serialization.Serializable


@Serializable
data class ResponseFollowerInfo(
    val login: String,
    val avatar_url: String
)
