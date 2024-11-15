package com.example.enishop.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.enishop.bo.Article
import com.example.enishop.vm.FormulaireAjoutVM
import kotlinx.coroutines.launch
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormulaireAjoutPage(modifier: Modifier = Modifier,
                        vm : FormulaireAjoutVM = hiltViewModel(),
                        onSavedClick : ()->Unit
) {
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
    var categoryState by remember { mutableStateOf( "") }
    //SnackBar
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { TopAppBar(
            title = { Text("Ajout article") }
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
                vm.addArticle(Article(
                    0L,
                    title,
                    description,
                    price.toDouble(),
                    "",
                    categoryState,
                    LocalDate.now()
                ))
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
                onSavedClick()

            },
                Modifier.fillMaxWidth()) {
                Text("Ajouter le produit")
            }

        }
    }
}