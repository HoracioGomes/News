package com.example.news.domain.usecase

import com.example.news.domain.repository.NewsRepository

class GetNewUseCase(private val repository: NewsRepository) {
}