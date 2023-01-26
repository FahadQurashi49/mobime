package com.mfaq.mobime.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class AdPhoto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,
    var url: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mobile_ad_id")
    @JsonIgnore
    var mobileAd: MobileAd?
)
