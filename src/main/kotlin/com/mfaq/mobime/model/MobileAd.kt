package com.mfaq.mobime.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.mfaq.mobime.dto.MobileAdDto
import jakarta.persistence.*
import java.sql.Timestamp


@Entity
data class MobileAd(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,
    var title: String,
    var brand: String,
    var state: String,
    var description: String,
    var price: Double,
    var createdAt: Timestamp?,
    var city: String,
    var area: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    var user: User?
) {
    constructor(mobileAdDto: MobileAdDto) :
            this(null, mobileAdDto.title, mobileAdDto.brand, mobileAdDto.state,
                mobileAdDto.description,  mobileAdDto.price, null,
                mobileAdDto.city,  mobileAdDto.area, null) {

    }
}
