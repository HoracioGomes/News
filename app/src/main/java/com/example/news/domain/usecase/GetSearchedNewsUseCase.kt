package com.example.news.domain.usecase

import com.example.news.data.model.APIResponse
import com.example.news.data.repository.Resource
import com.example.news.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val repository: NewsRepository) {

    suspend fun execute(country: String, page: Int, searchQuery: String): Resource<APIResponse> {
        return repository.getSearchedNews(country = country, page = page, searchQuery = searchQuery)
    }
}