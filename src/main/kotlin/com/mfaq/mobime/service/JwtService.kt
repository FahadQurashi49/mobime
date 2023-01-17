package com.mfaq.mobime.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import com.mfaq.mobime.model.User

@Component
class JwtService {
    private val key =  Keys.hmacShaKeyFor("MobimeSpringBootJsonWebTokenApplicationKey#9211".toByteArray())
    private val parser = Jwts.parserBuilder().setSigningKey(key).build()

    fun generate(userId: String): String {
        return Jwts.builder()
            .setSubject(userId)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
            .signWith(key).compact()
    }

    fun isValid(token: String, userId: String): Boolean {
        val claims = parser.parseClaimsJws(token).body
        val unexpired = claims.expiration.after(Date.from(Instant.now()))

        return unexpired && (claims.subject == userId)
    }
}