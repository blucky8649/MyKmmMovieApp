package com.example.mykmmmovieapp.di

import com.example.mykmmmovieapp.data.source.MovieDataSource
import com.example.mykmmmovieapp.data.source.MovieRepositoryImpl
import com.example.mykmmmovieapp.data.source.local.LocalMovieDataSource
import com.example.mykmmmovieapp.data.source.local.MovieDatabase
import com.example.mykmmmovieapp.data.source.remote.MovieApi
import com.example.mykmmmovieapp.data.source.remote.RemoteMovieDataSource
import com.example.mykmmmovieapp.domain.MovieRepository
import com.example.mykmmmovieapp.domain.usecases.GetMovieListUseCase
import org.koin.core.Koin
import org.koin.core.parameter.parametersOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import kotlin.reflect.KClass

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            movieModule,
            useCasesModule,
            platformMovieModule
        )
    }
// IOS
fun initKoin() = initKoin {}

val movieModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(
            get(qualifier = named("remote")),
            get(qualifier = named("local"))

        )
    }
    single<MovieDataSource>(named("local")) { LocalMovieDataSource(get()) }
    single<MovieDataSource>(named("remote")) { RemoteMovieDataSource(get()) }
    single { MovieDatabase(get()) }
    single { MovieApi() }
    single { GetMovieListUseCase(get()) }
}

val useCasesModule = module {

}

fun createKoinApp() = koinApplication {
    modules(movieModule, platformMovieModule, useCasesModule)
}

@Suppress("UNCHECKED_CAST")
fun <T> Koin.getDependency(clazz: KClass<*>): T {
    return get(clazz, null) { parametersOf(clazz.simpleName) } as T
}