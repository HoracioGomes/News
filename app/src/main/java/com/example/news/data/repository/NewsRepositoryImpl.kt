package com.example.news.data.repository

import com.example.news.data.model.APIResponse
import com.example.news.data.model.Article
import com.example.news.data.repository.datasource.NewsRemoteDataSource
import com.example.news.util.Converters
import com.example.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val dataSource: NewsRemoteDataSource) : NewsRepository {
    override suspend fun getNews(country: String, page: Int): Resource<APIResponse> {
        return Converters.responseToResource(dataSource.getNews(country, page))
    }

    override suspend fun getSearchedNews(country: String, page: Int,searchQuery: String): Resource<APIResponse> {
        return Converters.responseToResource(dataSource.getSearchNews(country = country, page = page, querySearch = searchQuery))
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}