package com.example.news.data.repository.datasource

import com.example.news.data.model.Article

interface NewsLocalDataSource {
    suspend fun saveArticleToDb(article: Article)
}