package com.example.enishop.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.enishop.repository.ArticleRepository
import com.example.enishop.ui.theme.Typography
import com.example.enishop.vm.DetailArticleVM
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailArticlePage(modifier: Modifier = Modifier,
                      idArticle: Long,
                      vm : DetailArticleVM = hiltViewModel()
) {
    //TODO VM
    val  article = vm.article.collectAsState().value
    vm.fetchArticleById(idArticle)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Détail Produit") }
            )
        }
    ) { innerPadding ->
        if(article == null){
            Text("Article inexistant :/")
        }else
        Column(modifier= Modifier
            .padding(innerPadding)
            .fillMaxWidth()){
            AsyncImage(
                model = article.urlImage,
                contentDescription = article.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            Spacer(Modifier.padding(8.dp))
            Row {
                Text(
                    article.name,
                    style = Typography.headlineLarge,
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    "${article.price}€",
                    style = Typography.bodyLarge,
                )

            }
            Spacer(Modifier.padding(8.dp))
            Text(article.description,
                style = Typography.bodyLarge )
            Spacer(Modifier.height(8.dp))
            Row {
                Text("Dispo le : ")
                Text(
                    DateTimeFormatter
                        .ofPattern("dd/MM/yyyy")
                        .format(article.date),
                )
            }

        }

    }
}
