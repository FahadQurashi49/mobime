package com.mfaq.mobime.service

import com.mfaq.mobime.dto.MobileAdDto
import com.mfaq.mobime.model.MobileAd
import com.mfaq.mobime.repository.MobileAdRepository
import com.mfaq.mobime.repository.UserRepository
import org.springframework.stereotype.Service
import java.sql.Timestamp;

@Service
class MobileAdService(private val mobileAdRepo: MobileAdRepository, private val userRepo: UserRepository) {

    fun getAllMobileAd(): Iterable<MobileAd> = mobileAdRepo.findAll()

    fun getAllMobileAdByTitle(title: String): Iterable<MobileAd> =
        mobileAdRepo.findAllByTitleContaining(title)

    fun createMobileAd(mobileAdDto: MobileAdDto): MobileAd {
        val mobileAd = MobileAd(mobileAdDto)
        mobileAd.createdAt = Timestamp(System.currentTimeMillis())
        mobileAd.user = userRepo.findById(mobileAdDto.userId).get()
        return mobileAdRepo.save(mobileAd)
    }

    fun getMobileAdById(id: Long): MobileAd = mobileAdRepo.findById(id).get()
}

