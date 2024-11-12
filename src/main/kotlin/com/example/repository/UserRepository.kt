package com.example.repository

import com.example.model.User
import java.util.UUID

class UserRepository {
    private val users= mutableListOf<User>()

    fun findAll():List<User> = users

    fun findByID(id:UUID):User? = users.firstOrNull{it.id==id}

    fun findByUsername(userName:String):User?=users.firstOrNull { it.userName==userName }

    fun saveUser(user: User):Boolean=users.add(user)

    fun updateUsername(user: User,userName: String):User? {
        val index = users.indexOf(user)
        if(index==-1) return null
        users[index].userName = userName
        return users[index]
    }
    fun deleteUser(user: User):Boolean=users.remove(user)
}