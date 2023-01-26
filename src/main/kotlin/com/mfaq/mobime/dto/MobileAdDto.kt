package com.mfaq.mobime.dto

import org.springframework.web.multipart.MultipartFile
import java.sql.Timestamp

data class MobileAdDto(
    var title: String,
    var brand: String,
    var state: String,
    var description: String,
    var price: Double,
    var createdAt: Timestamp?,
    var city: String,
    var area: String,
    var userId: String,
    var photo: Array<MultipartFile>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MobileAdDto

        if (title != other.title) return false
        if (brand != other.brand) return false
        if (state != other.state) return false
        if (description != other.description) return false
        if (price != other.price) return false
        if (createdAt != other.createdAt) return false
        if (city != other.city) return false
        if (area != other.area) return false
        if (userId != other.userId) return false
        if (!photo.contentEquals(other.photo)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + brand.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + city.hashCode()
        result = 31 * result + area.hashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + photo.contentHashCode()
        return result
    }
}
