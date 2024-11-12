package com.example.model

import java.util.UUID

data class User(
    val id:UUID,
    var userName:String,
    val password:String
)
