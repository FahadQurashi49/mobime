package com.mfaq.mobime.controller

import com.mfaq.mobime.dto.Message
import com.mfaq.mobime.dto.MobileAdDto
import com.mfaq.mobime.service.JwtService
import com.mfaq.mobime.service.MobileAdService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mobime/mobilead")
class MobileAdController(private val mobileAdService: MobileAdService, private val jwtService: JwtService) {
//    @GetMapping
//    fun getAllMobileAds() = mobileAdService.getAllMobileAd()

    @GetMapping
    fun getAllMobileAds(
        @RequestParam(value="title", defaultValue = "", required=true) title: String
    )
        = mobileAdService.getAllMobileAdByTitle(title)

    @PostMapping
    fun createMobileAd(@CookieValue("jwt") jwtCookie: String?,
                       @RequestBody mobileAd: MobileAdDto): ResponseEntity<Any> {
        try {
            if (jwtCookie == null) {
                return ResponseEntity.status(401).body(Message("unauthenticated"))
            }
            if (jwtService.isValid(jwtCookie, mobileAd.userId)) {
                return ResponseEntity.ok(mobileAdService.createMobileAd(mobileAd))
            }
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("unauthenticated"))
        }
        return ResponseEntity.status(401).body(Message("unauthenticated"))
    }

    @RequestMapping(value=["/{id}"])
    fun getMobileAd(@PathVariable id: Long) = mobileAdService.getMobileAdById(id)
}