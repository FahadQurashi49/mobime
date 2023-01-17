package com.mfaq.mobime.repository

import org.springframework.data.repository.CrudRepository

import com.mfaq.mobime.model.User

interface UserRepository: CrudRepository<User, String> {
}