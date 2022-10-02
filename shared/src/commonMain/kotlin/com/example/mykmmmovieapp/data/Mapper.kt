package com.example.mykmmmovieapp.data

import com.example.mykmmmovieapp.data.source.local.entity.MovieEntity
import com.example.mykmmmovieapp.data.source.local.entity.MovieItemEntity
import com.example.mykmmmovieapp.data.source.remote.dto.MovieApiResponseModel
import com.example.mykmmmovieapp.data.source.remote.dto.MovieItemDto
import com.example.mykmmmovieapp.domain.entity.Movie
import com.example.mykmmmovieapp.domain.entity.MovieItem

fun MovieEntity.toMovie(): Movie {
    return Movie(
        total, start, display, items
    )
}

fun MovieItemEntity.toMovieItem(): MovieItem {
    return MovieItem(
        id, title, subTitle, thumb, director, actor, publishedAt, rating, scrap, memo
    )
}

fun MovieItem.toMovieItemEntity(): MovieItemEntity {
    return MovieItemEntity(
        id, title, subTitle, thumb, director, actor, publishedAt, rating, scrap, memo
    )
}

fun MovieApiResponseModel.toMovie(): Movie {
    return Movie(
        total, start, display, items
    )
}

fun MovieItemDto.toMovieItem(): MovieItem {
    return MovieItem(
        id, title, subTitle, thumb, director, actor, publishedAt, rating, scrap
    )
}