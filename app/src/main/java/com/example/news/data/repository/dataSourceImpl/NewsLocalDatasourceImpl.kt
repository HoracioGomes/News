package com.example.news.data.repository.dataSourceImpl

import com.example.news.data.db.ArticleDAO
import com.example.news.data.model.Article
import com.example.news.data.repository.datasource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDatasourceImpl(private val dao: ArticleDAO): NewsLocalDataSource {
    override suspend fun saveArticleToDb(article: Article) {
        dao.saveArticle(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return dao.getSavedArticles()
    }

    override suspend fun deleteSavedArticle(article: Article) {
        dao.deleteSavedArticle(article)
    }
}