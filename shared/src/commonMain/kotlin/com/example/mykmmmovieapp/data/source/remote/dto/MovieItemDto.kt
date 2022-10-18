package com.example.mykmmmovieapp.data.source.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieItemDto(
    @SerialName("title")
    val title: String,
    @SerialName("subtitle")
    val subTitle: String? = null,
    @SerialName("link")
    val link: String? = null,
    @SerialName("image")
    val thumb: String? = null,
    @SerialName("director")
    val director: String? = null,
    @SerialName("actor")
    val actor: String? = null,
    @SerialName("pubDate")
    val publishedAt: String? = null,
    @SerialName("userRating")
    val rating: Float = 0f,
)