package com.mfaq.mobime.service

import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service
import java.io.File

@Service
class S3Service {
    fun uploadFile(file: File) : String? {
        try {
            val clientRegion = Regions.AP_SOUTH_1
            val bucketName = "mobime-prod-fq49"
//            val fileObjKeyName = "*** File object key name ***"
//            val fileName = "*** Path to file to upload ***"

            val s3Client: AmazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .build()

            val request = PutObjectRequest(bucketName, file.name, file)
//            val metadata = ObjectMetadata()
//            metadata.contentType = "plain/text"
//            metadata.addUserMetadata("title", "someTitle")
//            request.metadata = metadata
            s3Client.putObject(request)
            val url = s3Client.getUrl(bucketName, file.name)
            return url.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}