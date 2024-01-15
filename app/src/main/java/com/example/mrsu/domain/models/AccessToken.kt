package com.example.mrsu.domain.models

data class AccessToken(
    val access_token: String,
    val token_type: String,
    val expires_in: Long
)