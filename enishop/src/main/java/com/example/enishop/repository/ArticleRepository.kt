package com.example.enishop.repository

import com.example.enishop.bo.Article
import com.example.enishop.dao.ArticleDAO
import com.example.enishop.dao.DaoFactory
import com.example.enishop.dao.DaoType
import javax.inject.Inject

class ArticleRepository @Inject constructor(factory : DaoFactory) {
    val memoryDao = factory.getDao(DaoType.MEMORY)
    val networkDao = factory.getDao(DaoType.NETWORK)
    val dbDao = factory.getDao(DaoType.DATABASE)

    val isInternetAvailable = false

    val daoSelected : ArticleDAO
        get() = if(isInternetAvailable) networkDao else dbDao

    fun getAll() = daoSelected.findAll()
    fun getById(id: Long) = daoSelected.findById(id)

    fun add(article: Article) = daoSelected.insert(article)

    fun addAll(articles: List<Article>) =
        daoSelected.insertAll(articles)

    fun getCategories() = getAll().map { it.category }.distinct()
}