package com.example.mykmmmovieapp.di

import com.example.mykmmmovieapp.domain.entity.MovieItem
import com.example.mykmmmovieapp.domain.usecases.GetMovieListUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ViewModelHelper : KoinComponent {
    val getMovieListUseCase: GetMovieListUseCase by inject()
    suspend fun getMovieList(
        refresh: Boolean,
        searchQuery: String
    ) : List<MovieItem> = getMovieListUseCase(refresh, searchQuery)
}