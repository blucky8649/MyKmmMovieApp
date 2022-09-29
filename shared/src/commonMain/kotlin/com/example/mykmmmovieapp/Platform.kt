package com.example.mykmmmovieapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform