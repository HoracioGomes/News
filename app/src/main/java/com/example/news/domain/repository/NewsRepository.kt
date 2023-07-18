package com.example.news.domain.repository

import com.example.news.data.model.APIResponse
import com.example.news.data.model.Article
import com.example.news.data.repository.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(country: String, page: Int): Resource<APIResponse>
    suspend fun getSearchedNews(country: String, page: Int, searchQuery: String): Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>


}