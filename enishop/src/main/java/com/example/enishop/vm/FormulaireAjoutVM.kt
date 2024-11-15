package com.example.enishop.vm

import androidx.lifecycle.ViewModel
import com.example.enishop.bo.Article
import com.example.enishop.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FormulaireAjoutVM @Inject constructor
    (val repo : ArticleRepository): ViewModel() {
    fun addArticle(article: Article) {
        repo.add(article)
    }
}