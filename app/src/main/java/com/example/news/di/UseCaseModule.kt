package com.example.news.di

import com.example.news.domain.repository.NewsRepository
import com.example.news.domain.usecase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesGetNewsUseCase(
        repository: NewsRepository
    ): GetNewsUseCase {
        return GetNewsUseCase(repository)
    }
}