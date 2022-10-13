package com.example.mykmmmovieapp.data.source.local

import com.example.mykmmmovieapp.data.DatabaseDriverFactory
import com.example.mykmmmovieapp.data.source.local.entity.MovieEntity
import com.example.mykmmmovieapp.data.source.local.entity.MovieItemEntity
import com.example.mykmmmovieapp.data.source.remote.dto.MovieApiResponseModel
import com.example.mykmmmovieapp.db.AppDatabase
import com.example.mykmmmovieapp.domain.entity.MovieItem
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow

class MovieDatabase(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    fun upsertMovies(movies: List<MovieItemEntity>) {
        dbQuery.transaction {
            movies.forEach { movie ->
                dbQuery.upsertMovie(
                    id = movie.id,
                    title = movie.title,
                    subTitle = movie.subTitle,
                    thumb = movie.thumb,
                    director = movie.director,
                    actor = movie.actor,
                    publishedAt = movie.publishedAt,
                    rating = movie.rating,
                    link = movie.link
                )

            }
        }
    }
    fun getMovieList(): List<MovieItem> {
        return dbQuery.selectMovie(mapper = ::mapMovie).executeAsList()
    }

    fun clearMovies() {
        dbQuery.clearMovie()
    }

    fun mapMovie(
        id: Long,
        title: String,
        subTitle: String?,
        thumb: String?,
        director: String?,
        actor: String?,
        publishedAt: String?,
        rating: Float,
        link: String?

    ): MovieItem {
        return MovieItem(
            id = id,
            title = title,
            subTitle = subTitle,
            thumb = thumb,
            director = director,
            actor = actor,
            publishedAt = publishedAt,
            rating = rating,
            link = link
        )
    }
}