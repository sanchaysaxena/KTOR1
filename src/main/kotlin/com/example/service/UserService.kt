package com.example.service

import com.example.model.User
import com.example.repository.UserRepository
import java.util.UUID

class UserService(
    private val userRepository: UserRepository
) {
    fun findAll():List<User> = userRepository.findAll()

    fun findByID(id:String):User?=userRepository.findByID(id=UUID.fromString(id))

    fun findByUsername(username:String):User?=userRepository.findByUsername(username)

    fun saveUser(user:User):User?{
        if(findByUsername(user.userName)==null){
            userRepository.saveUser(user)
            return user
        }else return null
    }

    fun updateUsername(user: User,username: String):User?{
        if(findByUsername(user.userName)!=null){
            userRepository.updateUsername(user,username)
            return findByUsername(username)
        } else return null
    }
    fun deleteUser(user:User):Boolean=userRepository.deleteUser(user)
}