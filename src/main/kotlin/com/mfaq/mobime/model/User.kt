package com.mfaq.mobime.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class User(
    @Id
    var email: String,
    var name: String?,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String,
    @JsonIgnore
    var contact: String?
)
