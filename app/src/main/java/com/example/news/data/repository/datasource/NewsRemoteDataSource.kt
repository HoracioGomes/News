package com.example.news.data.repository.datasource

import com.example.news.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getNews(country: String, page: Int): Response<APIResponse>
    suspend fun getSearchNews(country: String, page: Int, querySearch: String): Response<APIResponse>
}