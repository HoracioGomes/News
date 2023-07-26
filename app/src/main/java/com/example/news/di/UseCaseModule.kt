package com.example.news.di

import com.example.news.domain.repository.NewsRepository
import com.example.news.domain.usecase.DeleteSavedNewsUseCase
import com.example.news.domain.usecase.GetNewsUseCase
import com.example.news.domain.usecase.GetSavedNewsUseCase
import com.example.news.domain.usecase.GetSearchedNewsUseCase
import com.example.news.domain.usecase.SaveNewsUseCase
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

    @Provides
    @Singleton
    fun providesSearchNewsUseCase(repository: NewsRepository): GetSearchedNewsUseCase {
        return GetSearchedNewsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesSaveArticleUseCase(repository: NewsRepository): SaveNewsUseCase {
        return SaveNewsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetSavedNewsUseCase(repository: NewsRepository): GetSavedNewsUseCase {
        return GetSavedNewsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesDeleteSavedArticleUseCase(repository: NewsRepository): DeleteSavedNewsUseCase {
        return DeleteSavedNewsUseCase(repository)
    }
}