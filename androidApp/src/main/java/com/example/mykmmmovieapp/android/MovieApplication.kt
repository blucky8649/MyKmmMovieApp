package com.example.mykmmmovieapp.android

import android.app.Application
import com.example.mykmmmovieapp.data.DatabaseDriverFactory
import com.example.mykmmmovieapp.data.source.MovieDataSource
import com.example.mykmmmovieapp.data.source.MovieRepositoryImpl
import com.example.mykmmmovieapp.data.source.local.LocalMovieDataSource
import com.example.mykmmmovieapp.data.source.local.MovieDatabase
import com.example.mykmmmovieapp.data.source.remote.MovieApi
import com.example.mykmmmovieapp.data.source.remote.RemoteMovieDataSource
import com.example.mykmmmovieapp.domain.MovieRepository
import com.example.mykmmmovieapp.domain.usecases.GetMovieListUseCase
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

@HiltAndroidApp
class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            modules(
                movieModule,
                viewModelModule
            )
        }
    }
    val viewModelModule = module {
        single { MainViewModel(get()) }
    }
    val movieModule = module {
        single<MovieRepository> {
            MovieRepositoryImpl(
                get(qualifier = named("local")),
                get(qualifier = named("remote"))
            )
        }
        factory<MovieDataSource>(named("local")) { LocalMovieDataSource(get()) }
        factory<MovieDataSource>(named("remote")) { RemoteMovieDataSource(get()) }
        factory { MovieDatabase(get()) }
        factory { MovieApi() }
        single { GetMovieListUseCase(get()) }
        single { DatabaseDriverFactory(this@MovieApplication) }
    }
}


