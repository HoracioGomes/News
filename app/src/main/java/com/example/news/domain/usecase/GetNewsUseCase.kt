package com.example.news.domain.usecase

import com.example.news.data.model.APIResponse
import com.example.news.data.util.Resource
import com.example.news.domain.repository.NewsRepository

class GetNewsUseCase(private val repository: NewsRepository) {

    suspend fun execute(country: String, page: Int): Resource<APIResponse> {
        return repository.getNews(country, page)
    }
}