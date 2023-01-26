package com.mfaq.mobime.repository

import com.mfaq.mobime.model.AdPhoto
import org.springframework.data.repository.CrudRepository

interface AdPhotoRepository: CrudRepository<AdPhoto, Long> {
}