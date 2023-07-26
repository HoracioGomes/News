package com.example.news.data.repository

import com.example.news.data.model.APIResponse
import com.example.news.data.model.Article
import com.example.news.data.repository.datasource.NewsLocalDataSource
import com.example.news.data.repository.datasource.NewsRemoteDataSource
import com.example.news.util.Converters
import com.example.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource
) : NewsRepository {
    override suspend fun getNews(country: String, page: Int): Resource<APIResponse> {
        return Converters.responseToResource(remoteDataSource.getNews(country, page))
    }

    override suspend fun getSearchedNews(
        country: String,
        page: Int,
        searchQuery: String
    ): Resource<APIResponse> {
        return Converters.responseToResource(
            remoteDataSource.getSearchNews(
                country = country,
                page = page,
                querySearch = searchQuery
            )
        )
    }

    override suspend fun saveNews(article: Article) {
        localDataSource.saveArticleToDb(article)
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return localDataSource.getSavedArticles()
    }
}