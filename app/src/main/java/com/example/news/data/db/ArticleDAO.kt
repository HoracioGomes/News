package com.example.news.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: Article)
    @Query("SELECT * FROM articles")
    fun getSavedArticles(): Flow<List<Article>>

    @Delete
    suspend fun deleteSavedArticle(article: Article)
}