package com.example

import com.example.data.DatabaseFactory
import com.example.plugins.configureSerialization
import com.example.repository.UserRepository
import com.example.routing.configureRouting
import com.example.service.UserService
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
    DatabaseFactory.init()
}

fun Application.module() {
    val userRepository=UserRepository()
    val userService=UserService(userRepository)

    configureSerialization()
    configureRouting(userService)
}
