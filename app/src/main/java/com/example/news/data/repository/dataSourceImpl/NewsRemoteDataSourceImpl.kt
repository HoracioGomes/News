package com.example.news.data.repository.dataSourceImpl

import com.example.news.data.api.NewsAPIService
import com.example.news.data.model.APIResponse
import com.example.news.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val service: NewsAPIService,
) : NewsRemoteDataSource {
    override suspend fun getNews(country: String, page: Int): Response<APIResponse> {
        return service.getNews(country = country, page = page)
    }

    override suspend fun getSearchNews(
        country: String,
        page: Int,
        querySearch: String
    ): Response<APIResponse> {
        return service.searchNews(country = country, page = page, querySearch = querySearch)
    }
}