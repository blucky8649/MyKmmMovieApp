package com.example.mykmmmovieapp.data.source.local

import com.example.mykmmmovieapp.data.source.MovieDataSource
import com.example.mykmmmovieapp.data.toMovieItemEntity
import com.example.mykmmmovieapp.domain.entity.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalMovieDataSource(
    private val db: MovieDatabase
): MovieDataSource {
    override suspend fun searchMovies(keyword: String): List<MovieItem> {
        throw UnsupportedOperationException()
    }

    override suspend fun searchMoviesAsFlow(keyword: String): Flow<List<MovieItem>> {
        throw UnsupportedOperationException()
    }

    override fun upsertMovies(movies: List<MovieItem>) {
        db.upsertMovies(movies.map { it.toMovieItemEntity() })
    }

    override fun getMovieList(): List<MovieItem> {
        return db.getMovieList()
    }

    override fun clearMovies() {
        db.clearMovies()
    }
}