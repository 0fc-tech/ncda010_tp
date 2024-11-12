package com.example.enishop.vm

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.enishop.bo.Article
import com.example.enishop.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ListeArticlesVM @Inject
constructor(repo:ArticleRepository) : ViewModel() {
    private val _listArticlesState = MutableStateFlow<List<Article>>(emptyList())
    val listArticlesState = _listArticlesState.asStateFlow()

    init {
        _listArticlesState.value = repo.getAll()
    }
}