package com.example.mykmmmovieapp.data.source

import com.example.mykmmmovieapp.domain.entity.Movie
import com.example.mykmmmovieapp.domain.entity.MovieItem
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    suspend fun searchMovies(keyword: String): List<MovieItem>
    fun upsertMovies(movies: List<MovieItem>)
    fun getMovieList() : Flow<List<MovieItem>>
    fun clearMovies()
}