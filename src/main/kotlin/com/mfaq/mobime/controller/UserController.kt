package com.mfaq.mobime.controller

import com.mfaq.mobime.dto.Message
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.mfaq.mobime.model.User
import com.mfaq.mobime.service.UserService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/mobime")
class UserController(private val userService: UserService) {

    @PostMapping("/signup")
    fun signup(@RequestBody user: User, response: HttpServletResponse): ResponseEntity<Message> {
        val jwt = userService.createUser(user)
        addJwtCookie(jwt, response)
        return ResponseEntity.ok(Message("success"))
    }

    @PostMapping("/login")
    fun login(@RequestBody user: User, response: HttpServletResponse): ResponseEntity<Any> {
        val jwt = userService.login(user)
            ?: ResponseEntity.badRequest().body(Message("User not found!"))
        addJwtCookie(jwt.toString(), response)

        return ResponseEntity.ok(Message("success"))
    }

    @GetMapping("logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Message> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("success"))
    }

    private fun addJwtCookie(jwt: String, response: HttpServletResponse) {
        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true
        response.addCookie(cookie)
    }











}