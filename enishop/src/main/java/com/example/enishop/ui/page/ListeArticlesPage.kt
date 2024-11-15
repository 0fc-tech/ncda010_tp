package com.example.enishop.ui.page

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.enishop.bo.Article
import com.example.enishop.ui.theme.Typography
import com.example.enishop.vm.ListeArticlesVM

private const val TAG = "ListeArticlesPage"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListeArticlesPage(modifier: Modifier = Modifier,
                      vm : ListeArticlesVM = hiltViewModel(),
                      onAddClick : ()->Unit,
                      onArticleClick : (id :Long )->Unit,
) {
    val listState = vm.articlesFltr.collectAsState()
    val categories = vm.listCategories.collectAsState()
    val categoriesSelected = vm.categoriesSelected.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Eni Shop") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onAddClick) {
                Icon(Icons.Filled.Add,"Add")
            }
        }
    ) { innerPadding ->
        Log.i(TAG, "ListeArticlesPage: ${innerPadding}")
        Column(Modifier.padding(innerPadding)) {
            RowFilters(categories, categoriesSelected, vm)
            ListeArticles(listState,onArticleClick)

        }
    }
}

@Composable
private fun ListeArticles(listState: State<List<Article>>,onArticleClick : (id :Long )->Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        Modifier.fillMaxSize()
    ) {
        items(listState.value) { article ->
            Card(Modifier.padding(8.dp).clickable { onArticleClick(article.id) }) {
                Column {
                    AsyncImage(
                        model = article.urlImage,
                        contentDescription = article.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                    Text(
                        article.name,
                        style = Typography.titleMedium,
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "${article.price}€",
                        style = Typography.bodyLarge,
                    )
                }
            }
        }
    }
}

@Composable
private fun RowFilters(
    categories: State<List<String>>,
    categoriesSelected: State<List<String>>,
    vm: ListeArticlesVM
) {
    LazyRow {
        items(categories.value) { category ->
            FilterChipArticle(
                filterName = category,
                selected = categoriesSelected.value.contains(category),
                onChipSelected = { filterName, isSelected ->
                    vm.selectCategory(filterName, isSelected)
                }
            )
            Spacer(Modifier.padding(end = 8.dp))
        }
    }
}


@Composable
fun FilterChipArticle(filterName:String,selected:Boolean,
                      onChipSelected:(filterName:String, isSelected:Boolean) -> Unit) {
    FilterChip(
        onClick = { onChipSelected(filterName,!selected) },
        label = { Text(filterName) },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {{
            Spacer(Modifier.width(16.dp))
        }},
    )
}