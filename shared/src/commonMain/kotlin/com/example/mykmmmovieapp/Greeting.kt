package com.example.mykmmmovieapp

import com.example.mykmmmovieapp.data.source.remote.MovieApi
import com.example.mykmmmovieapp.data.source.remote.dto.MovieApiResponseModel
import com.smp.shared.BuildConfig
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Greeting {
    private val platform: Platform = getPlatform()

    fun greeting(): String {
        return "Hello, ${platform.name}!"
    }
    suspend fun getHello(): String {
        return httpClient.get("https://dog.ceo/api/breeds/image/random").bodyAsText()
    }
    suspend fun searchMovies(keyword: String): MovieApiResponseModel {
        println("searchMovies Start!@!!")
        return httpClient.get(MovieApi.NEWS_ENDPOINT) {
            parameter("query", keyword)
            parameter("start", MovieApi.DEFAULT_PAGE_NUM)
            parameter("display", MovieApi.DEFAULT_PAGE_SIZE)
            headers {
                append("X-Naver-Client-Id", BuildConfig.CLIENT_ID)
                append("X-Naver-Client-Secret", BuildConfig.CLIENT_SECRET)
            }
        }.body()
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            }

            )
        }
    }
}