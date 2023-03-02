package com.mfaq.mobime.service

import com.mfaq.mobime.repository.UserRepository
import com.mfaq.mobime.model.User
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepo: UserRepository, private val jwtService: JwtService) {

    fun createUser(user: User): String {
        val loggedInUser = userRepo.save(user)
        return jwtService.generate(loggedInUser.email)
    }

    fun login(user: User): String? {
        try {
            val loggedInUser = userRepo.findById(user.email).get()
            if (loggedInUser.password == user.password) {
                return jwtService.generate(user.email)
            }
        } catch (e: Exception) {
            return null
        }
        return null
    }

    fun getUserById(email: String): User = userRepo.findById(email).get()







}