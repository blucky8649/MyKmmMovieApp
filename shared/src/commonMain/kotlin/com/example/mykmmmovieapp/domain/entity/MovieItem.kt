package com.example.mykmmmovieapp.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class MovieItem(
    val id: Long = -1L,
    val title: String,
    val subTitle: String? = null,
    val thumb: String? = null,
    val director: String? = null,
    val actor: String? = null,
    val publishedAt: String? = null,
    val rating: Float = 0f,
    val link: String? = null,
)