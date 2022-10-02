package com.example.mykmmmovieapp.di

import org.koin.core.module.Module
import com.example.mykmmmovieapp.data.source.MovieDataSource
import com.example.mykmmmovieapp.data.source.MovieRepositoryImpl
import com.example.mykmmmovieapp.data.source.local.LocalMovieDataSource
import com.example.mykmmmovieapp.data.source.local.MovieDatabase
import com.example.mykmmmovieapp.data.source.remote.MovieApi
import com.example.mykmmmovieapp.data.source.remote.RemoteMovieDataSource
import com.example.mykmmmovieapp.domain.MovieRepository
import com.example.mykmmmovieapp.domain.usecases.GetMovieListUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.koinApplication
import org.koin.dsl.module

expect val platformMovieModule: Module