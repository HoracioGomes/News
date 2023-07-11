package com.example.news.data.api

import com.example.news.BuildConfig
import com.example.news.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY,
        @Query("page")
        page: Int,
        @Query("country")
        country: String
    ): Response<APIResponse>
}