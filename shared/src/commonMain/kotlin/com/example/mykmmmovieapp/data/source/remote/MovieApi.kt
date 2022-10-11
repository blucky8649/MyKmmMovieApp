package com.example.mykmmmovieapp.data.source.remote

import com.example.mykmmmovieapp.data.source.remote.dto.MovieApiResponseModel
import com.example.mykmmmovieapp.util.createPlatformKtorLogger
import com.example.mykmmmovieapp.util.createPlatformLogger
import com.smp.shared.BuildConfig
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*

import io.ktor.client.plugins.BodyProgress.Plugin.install
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class MovieApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                encodeDefaults = true
            })
        }
        defaultRequest {
            header("X-Naver-Client-Id", BuildConfig.CLIENT_ID)
            header("X-Naver-Client-Secret", BuildConfig.CLIENT_SECRET)
        }
    }

    suspend fun searchMovies(keyword: String): MovieApiResponseModel {
        println("searchMovies Start!@!!")
        return httpClient.get(NEWS_ENDPOINT) {
            parameter("query", keyword)
            parameter("start", DEFAULT_PAGE_NUM)
            parameter("display", DEFAULT_PAGE_SIZE)
        }.body()
    }

    suspend fun searchMoviesS(keyword: String): String {
        println("searchMovies Start!@!!")
        return httpClient.get(NEWS_ENDPOINT) {
            parameter("query", keyword)
            parameter("start", DEFAULT_PAGE_NUM)
            parameter("display", DEFAULT_PAGE_SIZE)
        }.body()
    }

    companion object {
        const val DEFAULT_PAGE_NUM = 1
        const val DEFAULT_PAGE_SIZE = 50
        const val NEWS_ENDPOINT = "https://openapi.naver.com/v1/search/movie.json"
    }
}