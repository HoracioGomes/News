package com.example.news.di

import com.example.news.presentation.ui.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun providesNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }
}