package com.example.news.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.domain.usecase.GetNewsUseCase

class NewsViewModelFactory(
    private val application: Application,
    private val getNewsUseCase: GetNewsUseCase
) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(application = application, getNewsUseCase = getNewsUseCase) as T
    }
}