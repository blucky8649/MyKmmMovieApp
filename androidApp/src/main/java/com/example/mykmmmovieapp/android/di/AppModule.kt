package com.example.mykmmmovieapp.android.di

import android.app.Application
import com.example.mykmmmovieapp.data.DatabaseDriverFactory
import com.example.mykmmmovieapp.data.source.MovieDataSource
import com.example.mykmmmovieapp.data.source.MovieRepositoryImpl
import com.example.mykmmmovieapp.data.source.local.LocalMovieDataSource
import com.example.mykmmmovieapp.data.source.local.MovieDatabase
import com.example.mykmmmovieapp.data.source.remote.MovieApi
import com.example.mykmmmovieapp.data.source.remote.RemoteMovieDataSource
import com.example.mykmmmovieapp.domain.MovieRepository
import com.example.mykmmmovieapp.domain.entity.Movie
import com.example.mykmmmovieapp.domain.usecases.GetMovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabaseFactory(@ApplicationContext app: Application): DatabaseDriverFactory = DatabaseDriverFactory(app)

    @Provides
    @Singleton
    fun provideDatabase(driverFactory: DatabaseDriverFactory): MovieDatabase =
        MovieDatabase(driverFactory)

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi = MovieApi()

    @Provides
    @Singleton
    fun provideLocalDataSource(db: MovieDatabase): MovieDataSource = LocalMovieDataSource(db = db)

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: MovieApi): MovieDataSource = RemoteMovieDataSource(api)

    @Provides
    @Singleton
    fun provideMovieRepository(local: LocalMovieDataSource, remote: RemoteMovieDataSource): MovieRepository = MovieRepositoryImpl(remote, local)

    @Provides
    @Singleton
    fun provideGetMovieListUseCase(repository: MovieRepository) = GetMovieListUseCase(repository)
}