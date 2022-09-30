package com.example.mykmmmovieapp.domain.entity

import com.example.mykmmmovieapp.data.source.remote.dto.MovieItemDto

data class Movie(
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<MovieItemDto>
)