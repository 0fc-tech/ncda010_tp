package com.example.enishop.bo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    val name:String,
    val description:String,
    val price:Double,
    val urlImage:String,
    val category:String,
    //Là Room utilise le typeConverters
    val date: LocalDate,
)
