package com.example.mod2filmserie

val arrayListFilmSerie = arrayListOf<FilmSerie>()
fun main() {
    var choice = 0;
    do {
        menu()
        choice = readln().toIntOrNull() ?: 0
        when(choice){
            1-> addWatched()
            2-> addToWatch()
            3-> printAll()
        }
    }while (choice != 4)
}
fun menu(){
    println("Choisissez:")
    println("1 - Ajouter un film/série déjà vu:")
    println("2 - Ajouter un film/série à voir:")
    println("3 - Voir toute la liste:")
    println("4 - Exit:")
}
fun addWatched() {
    println("Nom du film/série déjà vu")
    val film = readln()
    arrayListFilmSerie.add(FilmSerie(film,true))
}
fun addToWatch() {
    println("Nom du film/série à voir")
    val film = readln()
    arrayListFilmSerie.add(FilmSerie(film,false))
}
fun printAll() {
    arrayListFilmSerie.forEach{filmSerie->
        print(filmSerie.name + " ")
        if(filmSerie.isWatched){
            println("a déjà été vu")
        }else{
            println("est à voir")
        }
    }
}