package com.example.data

import com.example.data.model.User
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

private val client= KMongo.createClient().coroutine
private val database= client.getDatabase("UserDatabase")

private val users= database.getCollection<User>()

suspend fun getUserByID(id:String):User?{
    return users.findOneById(id)
}

suspend fun saveUser(user: User):User?{
    if(getUserByID(user.id.toString()) ==null){
        users.insertOne(user)
        return user
    }else return null
}