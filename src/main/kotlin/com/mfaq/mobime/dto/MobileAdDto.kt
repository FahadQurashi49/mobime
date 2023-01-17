package com.mfaq.mobime.dto

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
    var userId: String
)
