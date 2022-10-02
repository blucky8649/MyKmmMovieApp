package com.example.mykmmmovieapp.android

import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mykmmmovieapp.android.di.viewModelModule
import com.example.mykmmmovieapp.data.DatabaseDriverFactory
import com.example.mykmmmovieapp.data.source.MovieDataSource
import com.example.mykmmmovieapp.data.source.MovieRepositoryImpl
import com.example.mykmmmovieapp.data.source.local.LocalMovieDataSource
import com.example.mykmmmovieapp.data.source.local.MovieDatabase
import com.example.mykmmmovieapp.data.source.remote.MovieApi
import com.example.mykmmmovieapp.data.source.remote.RemoteMovieDataSource
import com.example.mykmmmovieapp.di.initKoin
import com.example.mykmmmovieapp.domain.MovieRepository
import com.example.mykmmmovieapp.domain.usecases.GetMovieListUseCase
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module


class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin() {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
            androidContext(this@MovieApplication)
            modules(
                viewModelModule
            )
        }

    }
}


