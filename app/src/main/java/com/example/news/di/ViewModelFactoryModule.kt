package com.example.news.di

import android.app.Application
import com.example.news.domain.usecase.GetNewsUseCase
import com.example.news.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun providesNewsViewModelFactory(
        application: Application,
        getNewsUseCase: GetNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(application, getNewsUseCase)
    }
}