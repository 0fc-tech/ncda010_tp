package com.example.enishop.bo

import java.util.Date

data class Article(
    val id:Long,
    val name:String,
    val description:String,
    val price:Double,
    val urlImage:String,
    val category:String,
    val date:Date,
)
