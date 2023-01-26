package com.mfaq.mobime.service

import com.mfaq.mobime.dto.MobileAdDto
import com.mfaq.mobime.model.AdPhoto
import com.mfaq.mobime.model.MobileAd
import com.mfaq.mobime.repository.AdPhotoRepository
import com.mfaq.mobime.repository.MobileAdRepository
import com.mfaq.mobime.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.sql.Timestamp;
import java.util.*

@Service
class MobileAdService(private val mobileAdRepo: MobileAdRepository,
                      private val userRepo: UserRepository,
                      private val adPhotoRepo: AdPhotoRepository,
                      private val s3Service: S3Service) {

    fun getAllMobileAd(): Iterable<MobileAd> = mobileAdRepo.findAll()

    fun getAllMobileAdByTitle(title: String): Iterable<MobileAd> =
        mobileAdRepo.findAllByTitleContaining(title)

    fun createMobileAd(mobileAdDto: MobileAdDto): MobileAd {
        val mobileAd = MobileAd(mobileAdDto)
        mobileAd.createdAt = Timestamp(System.currentTimeMillis())
        mobileAd.user = userRepo.findById(mobileAdDto.userId).get()
        val savedMobileAd = mobileAdRepo.save(mobileAd)
        mobileAdDto.photo.forEach {
            val fileName = makeFileName(it)
            val photoFile = File(fileName)
            it.transferTo(photoFile)
            val photoUrl = s3Service.uploadFile(photoFile)
            if (photoUrl != null) {
                val adPhoto = AdPhoto(null, photoUrl, savedMobileAd)
                adPhotoRepo.save(adPhoto)
            }
        }
        return savedMobileAd
    }

    fun getMobileAdById(id: Long): MobileAd = mobileAdRepo.findById(id).get()

    private fun makeFileName(originalFile: MultipartFile): String {
        val uuid = UUID.randomUUID()
        val fileName = originalFile.originalFilename!!
        val extensionIndex = fileName.lastIndexOf('.')
        val newFileName = fileName.substring(0, extensionIndex) +
                "-" + uuid.toString() +
                fileName.substring(extensionIndex, fileName.length)
        return System.getProperty("java.io.tmpdir") + "/" + newFileName
    }
}

