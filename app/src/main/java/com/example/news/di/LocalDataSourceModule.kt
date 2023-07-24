package com.example.news.di

import com.example.news.data.db.ArticleDAO
import com.example.news.data.repository.dataSourceImpl.NewsLocalDatasourceImpl
import com.example.news.data.repository.datasource.NewsLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Provides
    @Singleton
    fun providesLocalDataSource(dao: ArticleDAO): NewsLocalDataSource {
        return NewsLocalDatasourceImpl(dao)
    }
}