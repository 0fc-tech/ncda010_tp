package com.example.enishop.bo

import java.time.LocalDate

data class Article(
    var id:Long,
    val name:String,
    val description:String,
    val price:Double,
    val urlImage:String,
    val category:String,
    val date: LocalDate,
)
