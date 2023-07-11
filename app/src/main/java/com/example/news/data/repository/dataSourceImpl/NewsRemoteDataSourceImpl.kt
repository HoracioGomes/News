package com.example.news.data.repository.dataSourceImpl

import com.example.news.data.api.NewsAPIService
import com.example.news.data.model.APIResponse
import com.example.news.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val service: NewsAPIService,
    private val country: String,
    private val page: Int
) : NewsRemoteDataSource {
    override suspend fun getNews(): Response<APIResponse> {
        return service.getNews(country = country, page = page)
    }
}