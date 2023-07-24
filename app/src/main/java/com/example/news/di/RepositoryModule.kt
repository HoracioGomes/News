package com.example.news.di

import com.example.news.data.repository.NewsRepositoryImpl
import com.example.news.data.repository.datasource.NewsLocalDataSource
import com.example.news.data.repository.datasource.NewsRemoteDataSource
import com.example.news.domain.repository.NewsRepository
import com.example.news.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(newsRemoteDataSource: NewsRemoteDataSource, newsLocalDataSource: NewsLocalDataSource): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource, newsLocalDataSource)
    }
}