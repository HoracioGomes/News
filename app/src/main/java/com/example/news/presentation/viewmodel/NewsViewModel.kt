package com.example.news.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.news.R
import com.example.news.data.model.APIResponse
import com.example.news.data.model.Article
import com.example.news.data.repository.Resource
import com.example.news.domain.usecase.DeleteSavedNewsUseCase
import com.example.news.domain.usecase.GetNewsUseCase
import com.example.news.domain.usecase.GetSavedNewsUseCase
import com.example.news.domain.usecase.GetSearchedNewsUseCase
import com.example.news.domain.usecase.SaveNewsUseCase
import com.example.news.util.NetworkUtil.Companion.isInternetAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel(
    private val application: Application,
    private val getNewsUseCase: GetNewsUseCase,
    private val searchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveArticleUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : AndroidViewModel(application) {

    val liveNews: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNews(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        liveNews.postValue(Resource.Loading())
        try {
            if (isInternetAvailable(application)) {
                val apiResult = getNewsUseCase.execute(country = country, page = page)
                liveNews.postValue(apiResult)
            } else {
                liveNews.postValue(Resource.Error(application.getString(R.string.internet_is_not_available)))
            }
        } catch (e: Exception) {
            liveNews.postValue(Resource.Error(e.message.toString()))
        }
    }

    val searchedNews: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun searchNews(country: String, page: Int, searchQuery: String) = viewModelScope
        .launch(Dispatchers.IO) {
            searchedNews.postValue(Resource.Loading())
            try {
                if (isInternetAvailable(application)) {
                    val apiResult = searchedNewsUseCase.execute(
                        country = country,
                        page = page,
                        searchQuery = searchQuery
                    )
                    searchedNews.postValue(apiResult)
                } else {
                    searchedNews.postValue(Resource.Error(application.getString(R.string.internet_is_not_available)))
                }
            } catch (exception: Exception) {
                searchedNews.postValue(Resource.Error(exception.message.toString()))
            }
        }

    fun saveArticle(article: Article) = viewModelScope.launch {
        saveArticleUseCase.execute(article)
    }

    fun getSavedNews() = liveData {
        getSavedNewsUseCase.execute().collect {
            emit(it)
        }
    }

    fun deleteSavedArticle(article: Article) = viewModelScope.launch {
        deleteSavedNewsUseCase.execute(article)
    }

}