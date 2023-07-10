package com.example.news.domain.usecase

import com.example.news.data.model.Article
import com.example.news.domain.repository.NewsRepository

class SaveNewsUseCase(private val repository: NewsRepository) {

    suspend fun execute(article: Article) = repository.saveNews(article)
}