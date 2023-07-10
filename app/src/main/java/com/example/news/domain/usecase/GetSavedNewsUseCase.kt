package com.example.news.domain.usecase

import com.example.news.domain.repository.NewsRepository

class GetSavedNewsUseCase(private val repository: NewsRepository) {
}