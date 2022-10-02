package com.example.mykmmmovieapp.domain

import com.example.mykmmmovieapp.domain.entity.MovieItem
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieList(refresh: Boolean, searchQuery: String): List<MovieItem>
}