package com.example.service

import com.auth0.jwt.JWT
import io.ktor.server.application.*
import kotlinx.io.files.Path
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.example.routing.request.LoginRequest
import java.util.*

class JwtService(
    private val application:Application,
    private val userService: UserService
) {
    //reading values from application.conf and creating objects from them
    private fun getConfigProperty(path:String)=application.environment.config.property(path).getString()

    private val secret=getConfigProperty("jwt.secret")
    private val issuer=getConfigProperty("jwt.issuer")
    private val audience=getConfigProperty("jwt.audience")
    val realm=getConfigProperty("jwt.realm")

    val jwtVerifier:JWTVerifier=
        JWT
            .require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()
    //this verifies what audience, issuer the JWT token must have if not request will be rejected with 401

    fun createJwtToken(loginRequest: LoginRequest):String?{
        val foundUser=userService.findByUsername(loginRequest.useName)

        return if(foundUser!= null && foundUser.password==loginRequest.password){
            JWT
                .create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim("userName",foundUser.userName)
                .withExpiresAt(Date(System.currentTimeMillis()+3_600_000))
                .sign(Algorithm.HMAC256(secret))
        } else null
    }


}