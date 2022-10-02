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

@HiltViewModel
class MainViewModel @Inject constructor(
    val GetMovieListUseCase: GetMovieListUseCase
): ViewModel() {
    init {
        viewModelScope.launch {
            GetMovieListUseCase(true, "서울").collectLatest {
                Log.d("ViewModel", it.toString())
            }
        }
    }
}