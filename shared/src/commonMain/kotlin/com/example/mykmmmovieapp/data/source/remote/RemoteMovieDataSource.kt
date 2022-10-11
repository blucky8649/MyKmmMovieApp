package com.example.mykmmmovieapp.data.source.remote

import com.example.mykmmmovieapp.data.source.MovieDataSource
import com.example.mykmmmovieapp.data.toMovieItem
import com.example.mykmmmovieapp.domain.entity.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteMovieDataSource(
    private val api: MovieApi
) : MovieDataSource {
    override suspend fun searchMovies(keyword: String): List<MovieItem> {
        return api.searchMovies(keyword)
            .items
            .map { movieItem ->
                println("MovieItem -> $movieItem")
                movieItem.toMovieItem().copy(id = movieItem.title.hashCode().toLong())
            }
    }

    override suspend fun searchMoviesAsFlow(keyword: String): Flow<List<MovieItem>> {
        return flow {
            val result = api.searchMovies(keyword).items.map {
                it.toMovieItem()
            }
            emit(result)
        }
    }

    override fun upsertMovies(movies: List<MovieItem>) {
        throw UnsupportedOperationException()
    }

    override fun getMovieList(): List<MovieItem> {
        throw UnsupportedOperationException()
    }

    override fun clearMovies() {
        throw UnsupportedOperationException()
    }
}