package com.example.news.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.domain.usecase.DeleteSavedNewsUseCase
import com.example.news.domain.usecase.GetNewsUseCase
import com.example.news.domain.usecase.GetSavedNewsUseCase
import com.example.news.domain.usecase.GetSearchedNewsUseCase
import com.example.news.domain.usecase.SaveNewsUseCase

class NewsViewModelFactory(
    private val application: Application,
    private val getNewsUseCase: GetNewsUseCase,
    private val searchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            application = application,
            getNewsUseCase = getNewsUseCase,
            searchedNewsUseCase = searchedNewsUseCase,
            saveArticleUseCase = saveNewsUseCase,
            getSavedNewsUseCase = getSavedNewsUseCase,
            deleteSavedNewsUseCase = deleteSavedNewsUseCase
        ) as T
    }
}