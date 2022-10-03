package com.example.mykmmmovieapp.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykmmmovieapp.domain.entity.MovieItem
import com.example.mykmmmovieapp.domain.usecases.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieUiState(
    val isLoading: Boolean = true,
    val movieList: List<MovieItem> = emptyList(),
    val searchQuery: String = "",
    )

class MainViewModel (
    val GetMovieListUseCase: GetMovieListUseCase
): ViewModel() {
    init {
        Log.d("ViewModel", "ViewModel Created..")
        viewModelScope.launch {
            GetMovieListUseCase(true, "서울").also { list ->
                _uiState.update { it.copy(isLoading = false, searchQuery = "서울", movieList = list) }
            }
        }
    }

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun fetchMovies(searchQuery: String) {
        fetchJob?.cancel()
        _uiState.update { it.copy(isLoading = true) }
        fetchJob = viewModelScope.launch {
            val movieList = GetMovieListUseCase(refresh = true, searchQuery = searchQuery)
            _uiState.update { it.copy(isLoading = false, searchQuery = searchQuery, movieList = movieList) }
        }
    }
}