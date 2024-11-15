package com.example.enishop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.enishop.ui.page.ListeArticlesPage
import com.example.enishop.ui.theme.TPTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.enishop.ui.page.DetailArticlePage
import com.example.enishop.ui.page.FormulaireAjoutPage

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TPTheme { NavHostApp() }
        }
    }
}

private const val ROUTE_LS_ARTICLES = "listarticles"
private const val ROUTE_ADD_ARTICLE = "addarticle"
private const val ROUTE_DETAIL_ARTICLE = "detailarticle"

@Composable
fun NavHostApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination :String = ROUTE_LS_ARTICLES
) {
    NavHost(modifier=modifier,navController = navController,
        startDestination =startDestination) {
        composable(ROUTE_LS_ARTICLES) {
            ListeArticlesPage(
                onArticleClick = {
                    navController.navigate("$ROUTE_DETAIL_ARTICLE/$it")
                },
                onAddClick = {
                    navController.navigate(ROUTE_ADD_ARTICLE)
                }
            )
        }
        composable(ROUTE_ADD_ARTICLE) { FormulaireAjoutPage(onSavedClick =
            {navController.popBackStack()})
        }
        composable("$ROUTE_DETAIL_ARTICLE/{idArt}") { entry->
            entry.arguments?.getString("idArt")?.toLongOrNull()?.let { idArticle->
                DetailArticlePage(idArticle = idArticle)
            }
        }
    }
}
