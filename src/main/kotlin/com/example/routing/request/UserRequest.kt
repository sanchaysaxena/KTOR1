package com.example.routing.request

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val useName:String,
    val password:String
)
