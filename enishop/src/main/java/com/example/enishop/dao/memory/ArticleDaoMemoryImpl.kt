package com.example.enishop.dao.memory

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.enishop.bo.Article
import com.example.enishop.dao.ArticleDAO
import java.time.Instant
import java.time.LocalDate
import java.util.Date
class ArticleDaoMemoryImpl :  ArticleDAO{
    val listArticles = arrayListOf(
        Article(
            id = 1,
            name = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            price = 109.95,
            urlImage = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            category = "men's clothing",
            date = LocalDate.now(),
            ),
        Article(    id = 2,
            name = "Mens Casual Premium Slim Fit T-Shirts",
            description = "Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.",
            price = 22.3,
            urlImage = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg",
            category = "men's clothing",
            date = LocalDate.now(),
        ),
        Article(
            id = 3,
            name = "Mens Cotton Jacket",
            description = "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your familymember. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.",
            price = 55.99,
            urlImage ="https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
            category = "men's clothing",
            date = LocalDate.now(),
        ),
        Article(
            id = 4,
            name = "Collier Diamant Solitaire",
            description = "Magnifique collier en or blanc 18 carats serti d'un diamant solitaire de 0.5 carat. Pureté VS1, couleur F. Chaîne ajustable et fermoir sécurisé. Livré dans son écrin.",
            price = 1299.99,
            urlImage = "https://fakestoreapi.com/img/collier-diamant.jpg",
            category = "jewelry",
            date = LocalDate.now(),
        ),
        Article(
            id = 5,
            name = "iPad Pro 12.9 pouces",
            description = "Dernière génération d'iPad Pro avec écran Liquid Retina XDR, puce M1, stockage 256GB. Parfait pour les créatifs et professionnels. Compatible Apple Pencil 2ème génération.",
            price = 1099.00,
            urlImage = "https://fakestoreapi.com/img/ipad-pro.jpg",
            category = "electronics",
            date = LocalDate.now(),
        ),
        Article(
            id = 6,
            name = "Robe de Soirée Élégante",
            description = "Sublime robe longue en soie avec détails en dentelle. Coupe sirène flatteuse, fendue sur le côté. Parfaite pour les événements formels et cérémonies. Disponible du 34 au 44.",
            price = 299.99,
            urlImage = "https://fakestoreapi.com/img/robe-soiree.jpg",
            category = "women's clothing",
            date = LocalDate.now(),
        ),
        Article(
            id = 7,
            name = "Set de Maquillage Professionnel",
            description = "Coffret complet incluant une palette de 35 fards à paupières, 5 rouges à lèvres, fond de teint, pinceaux professionnels. Formules longue tenue, pigments intenses.",
            price = 189.00,
            urlImage = "https://fakestoreapi.com/img/makeup-set.jpg",
            category = "cosmetics",
            date = LocalDate.now(),
        ),
        Article(
            id = 8,
            name = "Robot Culinaire Multifonction",
            description = "Robot cuisine tout-en-un avec 10 vitesses, bol 6L en inox, kit pâtisserie complet. Inclut hachoir, blender et livre de recettes. Puissance 1200W, garantie 5 ans.",
            price = 449.95,
            urlImage = "https://fakestoreapi.com/img/robot-cuisine.jpg",
            category = "home appliances",
            date = LocalDate.now(),
        ),
        Article(
            id = 9,
            name = "Lego Architecture Paris",
            description = "Reproduction détaillée de monuments parisiens emblématiques. Set collector de 1380 pièces incluant la Tour Eiffel, Le Louvre, l'Arc de Triomphe. Niveau expert, notice détaillée.",
            price = 89.99,
            urlImage = "https://fakestoreapi.com/img/lego-paris.jpg",
            category = "toys",
            date = LocalDate.now(),
        ),
        Article(
            id = 10,
            name = "Sneakers Limited Edition",
            description = "Édition limitée numérotée, collaboration artiste. Cuir premium, semelle cushion innovante. Design unique, boîte collector. Certificate d'authenticité inclus.",
            price = 259.99,
            urlImage = "https://fakestoreapi.com/img/sneakers-limited.jpg",
            category = "footwear",
            date = LocalDate.now(),
        )

    )

    override fun findById(id: Long): Article?
        = listArticles.find { it.id==id }

    override fun findAll(): List<Article>
        =listArticles

    override fun insert(article: Article): Long? {
        listArticles.add(article)
        return listArticles.lastIndexOf(article).toLong()
    }

    override fun insertAll(articles: List<Article>){
        listArticles.addAll(articles)
    }

}