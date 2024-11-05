package com.example.mod2bibliotheque

fun main() {
    val kotlinProg = Book("Kotlin Programming", "Jane Doe", 2021,
        "Education")
    val flutterPro = Book("Flutter Programming", "Majid", 2023,
        "Education")
    val harryPotter = Book("Harry Potter", "JK Rowling", 1995,
        "Novel")
    val jeanMichel = User("Jean Michel", 1)
    val robert = User("Robert Jackson", 2)
    jeanMichel.borrow(kotlinProg)
    robert.borrow(kotlinProg)
    robert.borrow(flutterPro)
    jeanMichel.returnItem(kotlinProg)
    robert.returnItem(flutterPro)
}
