package com.example.routing

import com.example.model.User
import com.example.routing.request.UserRequest
import com.example.routing.response.UserResponse
import com.example.service.UserService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.userRoute(userService: UserService) {
    post {
        // we involve receive<>() when we want to read JSON to any object
        val userRequest = call.receive<UserRequest>()

        val createdUser = userService.saveUser(
            user = userRequest.toModel()
        ) ?: return@post call.respond(HttpStatusCode.BadRequest)

        call.response.header(
            name = "id",
            value = createdUser.id.toString()
        )
        call.respond(message = HttpStatusCode.Created)
    }
    get {
        val users = userService.findAll()
        call.respond(message =users.map(User::toResponse))
    }

    get("/{id}"){
        val id:String=call.parameters["id"]
            ?: return@get call.respond(HttpStatusCode.BadRequest)
        val foundUser=userService.findByID(id)
            ?:return@get call.respond(HttpStatusCode.NotFound)
        call.respond(message = foundUser.toResponse())
    }
    delete("/{id}") {
        val id:String=call.parameters["id"]
            ?:return@delete call.respond(HttpStatusCode.BadRequest)
        val foundUser=userService.findByID(id)
            ?:return@delete call.respond(HttpStatusCode.NotFound)
        userService.deleteUser(foundUser)
        call.respond(message = HttpStatusCode.OK)
    }

}

private fun UserRequest.toModel(): User =
    User(
        id = UUID.randomUUID(),
        userName = this.useName,
        password = this.password,
    )

private fun User.toResponse():UserResponse = UserResponse(id=this.id,userName=this.userName)

// here we specify the handlers like POST/GET etc, i.e. every endpoint we would like to export
//Request headers are sent by the client to the server and contain information and instructions related to the requested resource,
// while response headers are sent by the server to the client and provide metadata, instructions, and additional information about the response itself.