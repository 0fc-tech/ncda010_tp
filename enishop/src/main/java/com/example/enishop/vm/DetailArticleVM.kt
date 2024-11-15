package com.example.enishop.vm

import androidx.lifecycle.ViewModel
import com.example.enishop.bo.Article
import com.example.enishop.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailArticleVM @Inject constructor
    (val repo : ArticleRepository) : ViewModel() {
    private val _article = MutableStateFlow<Article?>(null)
    val article = _article.asStateFlow()

    fun fetchArticleById(id:Long) {
        _article.update { repo.getById(id) }
    }
}