package com.example.news.data.repository.datasource

import com.example.news.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticleToDb(article: Article)
    fun getSavedArticles(): Flow<List<Article>>
    suspend fun deleteSavedArticle(article: Article)
}