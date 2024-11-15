package com.example.enishop.di

import android.content.Context
import androidx.room.Room
import com.example.enishop.dao.DaoFactory
import com.example.enishop.dao.db.ShopDB
import com.example.enishop.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDIModule {
    //Injection du Repository
    @Provides
    @Singleton
    fun provideArticleRepository(factory : DaoFactory) =
        ArticleRepository(factory)

    @Provides
    @Singleton
    fun provideDaoFactory(shopDB: ShopDB) =
        DaoFactory(shopDB)

    @Singleton
    @Provides
    fun provideShopDB(@ApplicationContext appContext: Context): ShopDB {
        return Room
            .databaseBuilder(appContext, ShopDB::class.java, "Shop.db")
            .build()
    }
}
