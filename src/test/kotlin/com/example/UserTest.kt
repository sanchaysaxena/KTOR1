package com.example

import com.example.plugins.configureSerialization
import com.example.repository.UserRepository
import com.example.routing.configureRouting
import com.example.service.UserService
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.utils.EmptyContent.status
import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class UserTest {
    @Test
    fun testUser() = testApplication {
        application {
            val userRepository= UserRepository()
            val userService=UserService(userRepository)

            configureSerialization()
            configureRouting(userService)
        }
        val user=client.get("/api/user").apply {
            assertEquals(HttpStatusCode.Companion.OK, status)
        }

    }
}