package com.mfaq.mobime.controller

import com.mfaq.mobime.dto.Message
import com.mfaq.mobime.dto.MobileAdDto
import com.mfaq.mobime.service.JwtService
import com.mfaq.mobime.service.MobileAdService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/mobime/mobilead")
class MobileAdController(private val mobileAdService: MobileAdService,
                         private val jwtService: JwtService) {

    @GetMapping
    fun getAllMobileAds(
        @RequestParam(value="title", defaultValue = "", required=true) title: String
    )
        = mobileAdService.getAllMobileAdByTitle(title)

    @PostMapping
    fun createMobileAd(@CookieValue("jwt") jwtCookie: String?,
                        @ModelAttribute mobileAdDto: MobileAdDto,
                        request: HttpServletRequest
                       ): ResponseEntity<Any> {
        try {
            if (jwtCookie == null) {
                return ResponseEntity.status(401).body(Message("unauthenticated"))
            }
            if (jwtService.isValid(jwtCookie, mobileAdDto.userId)) {
                return ResponseEntity.ok(mobileAdService.createMobileAd(mobileAdDto))
            }
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("unauthenticated"))
        }
        return ResponseEntity.status(401).body(Message("unauthenticated"))
    }

    @RequestMapping(value=["/{id}"])
    fun getMobileAd(@PathVariable id: Long): ResponseEntity<Any> {
        val mobileAd = mobileAdService.getMobileAdById(id)
        return ResponseEntity.ok(mobileAd)
    }

        /*  algo for saving file locally
        val resourceFolder = resourceLoader.getResource("classpath:static/images/")
        mobileAdDto.photo.forEach {
            val filePath = resourceFolder.file.absolutePath + "/" + it.originalFilename
            val file = File(filePath)
            if (!file.exists()) {
                file.createNewFile()
            }
            it.transferTo(file)
        }*/
}