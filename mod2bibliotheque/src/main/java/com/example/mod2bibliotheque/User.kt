package com.example.mod2bibliotheque


class User (val name:String, id : Int){
    fun borrow(book: Book)=book.borrow(this)
    fun returnItem(book: Book)=book.returnItem(this)

}