package com.example.news.domain.usecase

import com.example.news.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val repository: NewsRepository) {
}