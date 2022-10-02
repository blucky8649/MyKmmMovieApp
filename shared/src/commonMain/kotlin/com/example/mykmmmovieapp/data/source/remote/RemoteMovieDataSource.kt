package com.example.mykmmmovieapp.data.source.remote

import com.example.mykmmmovieapp.data.source.MovieDataSource
import com.example.mykmmmovieapp.data.toMovieItem
import com.example.mykmmmovieapp.domain.entity.MovieItem
import kotlinx.coroutines.flow.Flow

class RemoteMovieDataSource(
    private val api: MovieApi
) : MovieDataSource {
    override suspend fun searchMovies(keyword: String): List<MovieItem> {
        return api.searchMovies(keyword)
            .items
            .map { movieItem ->
                movieItem.copy(
                    id = movieItem.title.hashCode().toLong(),
                ).toMovieItem()
            }
    }

    override fun upsertMovies(movies: List<MovieItem>) {
        throw UnsupportedOperationException()
    }

    override fun getMovieList(): Flow<List<MovieItem>> {
        throw UnsupportedOperationException()
    }

    override fun clearMovies() {
        throw UnsupportedOperationException()
    }
}