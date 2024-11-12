package com.example.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

//in ktor we must register plugins, so here we are registering serialisation plugin
fun Application.configureSerialization(){
    install(ContentNegotiation){
        //ContentNegotiation - negotiates the media b/w client and server and serialize and deserialize the contents
        //in specific format, in this case the json format
        json()
    }
}