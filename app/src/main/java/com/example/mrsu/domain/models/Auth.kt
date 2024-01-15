package com.example.mrsu.domain.models

data class Auth(
    val grant_type:String,
    val client_id: String,
    val client_secret: String,
    val username : String,
    val password : String
)
