package com.example.enishop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.enishop.bo.Article
import com.example.enishop.repository.ArticleRepository
import com.example.enishop.ui.theme.TPTheme
import com.example.enishop.ui.theme.Typography
import com.example.enishop.vm.ListeArticlesVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val articleDisplayed = ArticleRepository.getAll()[0]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TPTheme {
                //DetailProduitPage(
                //    article = articleDisplayed)
                //FormulaireAjoutPage()
                ListeArticlesPage()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormulaireAjoutPage(modifier: Modifier = Modifier) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    //Liste déroulante Catégories
    val categories = listOf(
        "Electronics",
        "Jewelry",
        "Men's Clothing",
        "Women's Clothing" )
    var expanded by remember { mutableStateOf(false) }
    var categoryState by remember { mutableStateOf( "")}
    //SnackBar
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { TopAppBar(
            title = {Text("Ajout article")}
        ) }
    ) { innerPadding->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(16.dp)) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Titre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            TextField(
                value = description,
                onValueChange = {
                    description = it
                },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            TextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Prix") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                modifier = Modifier.fillMaxWidth(),
                onExpandedChange = { expanded = !expanded },
            ) {
                TextField(
                    value =categoryState ,
                    label = { Text("Catégorie") },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    onValueChange={},
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "")
                    },

                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(expanded = expanded,
                    onDismissRequest = { expanded = false }) {
                    categories.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                categoryState = selectionOption
                                expanded = false
                            },
                            text = {
                                Text(text = selectionOption)
                            }
                        )
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            Button(onClick = {
                scope.launch {
                    snackbarHostState
                        .showSnackbar(
                            message =
                                "Produit ajouté :" +
                                " $title $description $price €",
                            // Defaults to SnackbarDuration.Short
                            duration = SnackbarDuration.Long
                        )
                }

            },
                Modifier.fillMaxWidth()) {
                Text("Ajouter le produit")
            }

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProduitPage(modifier: Modifier = Modifier,
                      article: Article) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text("Détail Produit")}
            )
        }
    ) { innerPadding ->
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListeArticlesPage(modifier: Modifier = Modifier,
                      vm : ListeArticlesVM = viewModel()) {
    val state = vm.listArticlesState.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text("Eni Shop")}
            )
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                Modifier.fillMaxSize()
            ) {
                items(state.value) { article ->
                    Card(Modifier.padding(8.dp)) {
                        Column{
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
    }
}