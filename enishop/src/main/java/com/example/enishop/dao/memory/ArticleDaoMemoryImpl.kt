package com.example.enishop.dao.memory

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.enishop.bo.Article
import com.example.enishop.dao.ArticleDAO
import java.time.Instant
import java.util.Date
class ArticleDaoMemoryImpl :  ArticleDAO{
    val listArticles = arrayListOf(
        Article(1,
            "Téléphone","Tel",
            1500.0,"ht","HighTech",
            Date()
        ),
        Article(2,
            "PC","PC",
            2500.0,"ht","HighTech",
            Date()
        ),
        Article(3,
            "Voiture","Mobile",
            10500.0,"ht","Auto",
            Date()
        ),
    )

    override fun findById(id: Long): Article?
        = listArticles.find { it.id==id }

    override fun findAll(): List<Article>
        =listArticles

    override fun insert(article: Article): Long? {
        listArticles.add(article)
        return listArticles.lastIndexOf(article).toLong()
    }

    override fun insertAll(articles: List<Article>){
        listArticles.addAll(articles)
    }

}