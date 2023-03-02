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
    var user: User?,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mobileAd")
    var photos: List<AdPhoto>? = null
) {
    constructor(mobileAdDto: MobileAdDto) :
            this(null, mobileAdDto.title, mobileAdDto.brand, mobileAdDto.state,
                mobileAdDto.description,  mobileAdDto.price, null,
                mobileAdDto.city,  mobileAdDto.area, null, null) {

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MobileAd

        if (id != other.id) return false
        if (title != other.title) return false
        if (brand != other.brand) return false
        if (state != other.state) return false
        if (description != other.description) return false
        if (price != other.price) return false
        if (createdAt != other.createdAt) return false
        if (city != other.city) return false
        if (area != other.area) return false
        if (user != other.user) return false
        if (photos != other.photos) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + title.hashCode()
        result = 31 * result + brand.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + city.hashCode()
        result = 31 * result + area.hashCode()
        result = 31 * result + (user?.hashCode() ?: 0)
        result = 31 * result + (photos?.hashCode() ?: 0)
        return result
    }
}
