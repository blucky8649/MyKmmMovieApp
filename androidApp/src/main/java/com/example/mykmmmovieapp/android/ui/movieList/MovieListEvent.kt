package com.example.mykmmmovieapp.android.ui.movieList

sealed class MovieListEvent {
    object Refresh: MovieListEvent()
    data class OnSearchQueryChange(val searchQuery: String): MovieListEvent()
}
