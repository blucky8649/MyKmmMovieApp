package com.example.mykmmmovieapp.domain.usecases

import com.example.mykmmmovieapp.domain.MovieRepository
import com.example.mykmmmovieapp.domain.entity.MovieItem
import kotlinx.coroutines.flow.Flow

class GetMovieListUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(refresh: Boolean, searchQuery: String): List<MovieItem> {
        if (refresh && searchQuery.isBlank()) {
            // 만약 검색 쿼리가 빈값이면 그냥 이전 데이터 그대로 노출
            return movieRepository.getMovieList(!refresh, searchQuery)
        }
        return movieRepository.getMovieList(refresh, searchQuery)
    }
}