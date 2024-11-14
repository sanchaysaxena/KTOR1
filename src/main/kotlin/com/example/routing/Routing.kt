package com.example.routing

import com.example.service.UserService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    userService: UserService
){
    routing {
        route("/api/user"){
            userRoute(userService)
        }
    }
}

//here we instruct ktor that whenever a request is made to /api/user it should look for a handle under userRoute