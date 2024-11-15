package com.example.enishop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enishop.bo.Article
import com.example.enishop.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormulaireAjoutVM @Inject constructor
    (val repo : ArticleRepository): ViewModel() {

    fun addArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.add(article)
        }

    }
}