package com.example.mykmmmovieapp.data.source.remote

import com.example.mykmmmovieapp.data.source.remote.dto.MovieApiResponseModel
import com.example.mykmmmovieapp.util.createPlatformKtorLogger
import com.example.mykmmmovieapp.util.createPlatformLogger
import com.smp.shared.BuildConfig
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class MovieApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
        install(Logging) {
            logger = createPlatformKtorLogger("Network")
            level = LogLevel.NONE
        }

    }
    suspend fun searchMovies(keyword: String): MovieApiResponseModel {
        return httpClient.get(NEWS_ENDPOINT) {
            parameter("query", keyword)
            parameter("start", DEFAULT_PAGE_NUM)
            parameter("display", DEFAULT_PAGE_SIZE)
            headers {
                append("X-Naver-Client-Id", BuildConfig.CLIENT_ID)
                append("X-Naver-Client-Secret", BuildConfig.CLIENT_SECRET)
            }
        }
    }
    companion object {
        private const val DEFAULT_PAGE_NUM = 1
        private const val DEFAULT_PAGE_SIZE = 50
        private const val NEWS_ENDPOINT = "https://openapi.naver.com/v1/search/movie"
    }
}