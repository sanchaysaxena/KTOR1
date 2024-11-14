package com.example.routing.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    val oldUsername:String,
    val newUsername:String,
)
