package com.example.enishop.di

import com.example.enishop.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDIModule {
    @Provides
    @Singleton
    fun provideArticleRepository() = ArticleRepository
}
