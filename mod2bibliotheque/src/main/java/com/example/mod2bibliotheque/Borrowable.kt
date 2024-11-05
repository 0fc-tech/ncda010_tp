package com.example.mod2bibliotheque

interface Borrowable {
    fun borrow(user : User) : Boolean
    fun returnItem(user : User)
}