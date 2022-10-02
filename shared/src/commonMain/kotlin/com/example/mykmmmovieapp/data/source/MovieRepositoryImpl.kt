package com.example.mykmmmovieapp.data.source

import com.example.mykmmmovieapp.data.source.local.LocalMovieDataSource
import com.example.mykmmmovieapp.data.source.remote.RemoteMovieDataSource
import com.example.mykmmmovieapp.domain.MovieRepository
import com.example.mykmmmovieapp.domain.entity.MovieItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MovieRepositoryImpl(
    private val remoteMovieDataSource: MovieDataSource,
    private val localMovieDataSource: MovieDataSource
): MovieRepository {
    private val moviesMutex = Mutex()
    private var page = 0

    override suspend fun getMovieList(refresh: Boolean, searchQuery: String): Flow<List<MovieItem>> {
        if (refresh) {
            remoteMovieDataSource.searchMovies(searchQuery).also { networkResult ->
                moviesMutex.withLock {
                    localMovieDataSource.clearMovies()
                    localMovieDataSource.upsertMovies(networkResult)
                }
            }
        }
        return localMovieDataSource.getMovieList()
    }
}