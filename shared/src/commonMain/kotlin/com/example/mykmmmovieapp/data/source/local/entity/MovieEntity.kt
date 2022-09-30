package com.example.mykmmmovieapp.data.source.local.entity

import com.example.mykmmmovieapp.data.source.remote.dto.MovieItemDto

data class MovieEntity(
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<MovieItemDto>
)