package com.example.news.domain.usecase

import com.example.news.domain.repository.NewsRepository

class SaveNewsUseCase(private val repository: NewsRepository) {
}