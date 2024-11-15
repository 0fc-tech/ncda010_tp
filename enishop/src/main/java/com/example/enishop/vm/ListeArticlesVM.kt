package com.example.enishop.vm

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.enishop.bo.Article
import com.example.enishop.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListeArticlesVM @Inject
constructor(val repo:ArticleRepository) : ViewModel() {
    //Liste Source
    private val _articlesSrc = MutableStateFlow<List<Article>>(emptyList())
    //Liste Filtrées
    private val _articlesFltr = MutableStateFlow<List<Article>>(emptyList())
    val articlesFltr = _articlesFltr.asStateFlow()
    //Liste Catégorie
    private val _categoriesSelected = MutableStateFlow<List<String>>(emptyList())
    val categoriesSelected = _categoriesSelected.asStateFlow()

    var listCategories =MutableStateFlow(emptyList<String>())

    init {
        viewModelScope.launch(Dispatchers.IO)  {
            initList()
        }
    }

    suspend fun initList(){
        viewModelScope.launch(Dispatchers.IO) {
            _articlesSrc.value = repo.getAll()
            listCategories.value = repo.getCategories()
            _articlesFltr.value = _articlesSrc.value
        }
    }

    fun selectCategory(categorySelected:String,isSelected : Boolean) {
        if (isSelected)
            _categoriesSelected.value += categorySelected
        else
            _categoriesSelected.value -= categorySelected
        if(_categoriesSelected.value.isEmpty()){
            _articlesFltr.update {_articlesSrc.value }
        }
        else{
            _articlesFltr.update {_articlesSrc.value.filter {
                _categoriesSelected.value.contains(it.category)  }
            }
        }
    }
}

