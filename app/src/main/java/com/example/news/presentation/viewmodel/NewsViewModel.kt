package com.example.news.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.news.R
import com.example.news.data.model.APIResponse
import com.example.news.data.repository.Resource
import com.example.news.domain.usecase.GetNewsUseCase
import com.example.news.util.NetworkUtil.Companion.isInternetAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val application: Application,
    private val getNewsUseCase: GetNewsUseCase
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


}