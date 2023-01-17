package com.mfaq.mobime.repository

import com.mfaq.mobime.model.MobileAd
import org.springframework.data.domain.Page
import org.springframework.data.repository.CrudRepository

interface MobileAdRepository : CrudRepository<MobileAd, Long> {
    override fun findAll(): Iterable<MobileAd>
    fun findAllByTitleContaining(title: String): Iterable<MobileAd>


}