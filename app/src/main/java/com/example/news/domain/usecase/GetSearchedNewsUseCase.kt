package com.example.news.domain.usecase

import com.example.news.data.model.APIResponse
import com.example.news.data.model.Article
import com.example.news.data.util.Resource
import com.example.news.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val repository: NewsRepository) {

    suspend fun execute(searchQuery: String): Resource<APIResponse> {
        return repository.getSearchedNews(searchQuery)
    }
}