package com.example.mykmmmovieapp

import com.example.mykmmmovieapp.di.createKoinApp
import com.example.mykmmmovieapp.domain.MovieRepository
import com.example.mykmmmovieapp.domain.entity.MovieItem
import com.example.mykmmmovieapp.util.createPlatformLogger

class MovieSDKForIOS {

    private val logger = createPlatformLogger("MovieSDK")

    private val koinApp = createKoinApp()
    private val movieRepository: MovieRepository = koinApp.koin.get()


    @Throws(Exception::class)
    suspend fun searchMovies(refresh: Boolean, keyword:String): List<MovieItem> {
        return movieRepository.getMovieList(refresh, keyword)
    }

}