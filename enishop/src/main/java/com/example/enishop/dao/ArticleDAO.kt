package com.example.enishop.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.enishop.bo.Article
@Dao
interface ArticleDAO {
    @Query("SELECT * FROM Article WHERE id=:id")
    fun findById(id:Long): Article?
    @Query("SELECT * FROM Article")
    fun findAll(): List<Article>
    @Insert
    fun insert(article:Article): Long?
    @Insert
    fun insertAll(articles:List<Article>)

}