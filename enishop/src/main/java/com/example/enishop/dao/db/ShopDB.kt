package com.example.enishop.dao.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.enishop.bo.Article
import com.example.enishop.dao.ArticleDAO
import com.example.enishop.util.Converters

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class ShopDB : RoomDatabase() {
    abstract fun articleDao() : ArticleDAO
}