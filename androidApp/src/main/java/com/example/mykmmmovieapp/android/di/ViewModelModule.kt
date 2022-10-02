package com.example.mykmmmovieapp.android.di

import com.example.mykmmmovieapp.android.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel(get()) }
}