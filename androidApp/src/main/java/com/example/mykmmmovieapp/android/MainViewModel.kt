package com.example.mykmmmovieapp.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykmmmovieapp.domain.usecases.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel (
    val GetMovieListUseCase: GetMovieListUseCase
): ViewModel() {
    init {
        Log.d("ViewModel", "ViewModel Created..")
        viewModelScope.launch {
            GetMovieListUseCase(true, "서울")
        }
    }
}