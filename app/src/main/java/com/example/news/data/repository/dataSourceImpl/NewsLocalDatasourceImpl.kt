package com.example.news.data.repository.dataSourceImpl

import com.example.news.data.db.ArticleDAO
import com.example.news.data.model.Article
import com.example.news.data.repository.datasource.NewsLocalDataSource

class NewsLocalDatasourceImpl(private val dao: ArticleDAO): NewsLocalDataSource {
    override suspend fun saveArticleToDb(article: Article) {
        dao.saveArticle(article)
    }
}