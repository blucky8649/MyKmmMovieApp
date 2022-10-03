package com.example.mykmmmovieapp.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykmmmovieapp.android.ui.movieList.MovieListEvent
import com.example.mykmmmovieapp.domain.entity.MovieItem
import com.example.mykmmmovieapp.domain.usecases.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieUiState(
    val isLoading: Boolean = true,
    val movieList: List<MovieItem> = emptyList(),
    val searchQuery: String = "서울",
    )

class MainViewModel (
    val GetMovieListUseCase: GetMovieListUseCase
): ViewModel() {
    init {
        fetchMovies()
    }

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun onSearchEvent(event: MovieListEvent) {
        when(event) {
            is MovieListEvent.Refresh -> {
                fetchMovies(refresh = true)
            }
            is MovieListEvent.OnSearchQueryChange -> {
                fetchJob?.cancel()
                fetchJob = viewModelScope.launch {
                    delay(500L)
                    fetchMovies(true, event.searchQuery)
                }

            }
        }
    }

    fun fetchMovies(
        refresh: Boolean = false,
        searchQuery: String = uiState.value.searchQuery
    ) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val movieList = GetMovieListUseCase(refresh = true, searchQuery = searchQuery)
            _uiState.update { it.copy(isLoading = false, searchQuery = searchQuery, movieList = movieList) }
        }
    }
}