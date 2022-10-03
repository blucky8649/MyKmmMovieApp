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
    val isLoading: Boolean = false,
    val movieList: List<MovieItem> = emptyList(),
    val searchQuery: String = "서울",
    val isRefreshing: Boolean = false
    )

class MainViewModel (
    val GetMovieListUseCase: GetMovieListUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchMovies()
    }

    private var fetchJob: Job? = null

    fun onSearchEvent(event: MovieListEvent) {
        when(event) {
            is MovieListEvent.Refresh -> {
                fetchMovies(refresh = true)
            }
            is MovieListEvent.OnSearchQueryChange -> {
                _uiState.update { it.copy(searchQuery = event.searchQuery) }
                fetchJob?.cancel()
                fetchJob = viewModelScope.launch {
                    delay(500L)
                    fetchMovies(true)
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