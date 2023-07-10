package com.example.news.domain.usecase

import com.example.news.data.model.APIResponse
import com.example.news.data.model.Article
import com.example.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val repository: NewsRepository) {

    fun execute(): Flow<List<Article>> {
        return repository.getSavedNews()
    }

}