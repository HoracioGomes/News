package com.example.news.domain.repository

import com.example.news.data.model.APIResponse
import com.example.news.data.model.Article
import com.example.news.data.util.Resource

interface NewsRepository {

    suspend fun getNews(): Resource<APIResponse>
    suspend fun getSearchedNews(): Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): List<Article>


}