package com.example.mod2bibliotheque

class Book(
    title : String,
    author : String,
    releaseYear : Int,
    val genre : String
) : Item(title,author,releaseYear),Borrowable {
    override fun borrow(user: User): Boolean {
        if (isAvailable) {
            isAvailable = false
            println("$title has been borrowed by ${user.name}")
            return true
        }else{
            println("$title is not available for borrowing")
        }
        return false
    }


    override fun returnItem(user: User) {
        isAvailable = true
        println("$title has been returned by ${user.name}")
    }

}